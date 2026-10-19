package com.army.btsmusic.player

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class YouTubeAudioEngine(private val context: Context) {

    private val mainHandler = Handler(Looper.getMainLooper())
    private var webView: WebView? = null
    private var isPlayerReady = false
    private var pendingVideoId: String? = null

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentPositionMs = MutableStateFlow(0L)
    val currentPositionMs: StateFlow<Long> = _currentPositionMs.asStateFlow()

    private val _durationMs = MutableStateFlow(0L)
    val durationMs: StateFlow<Long> = _durationMs.asStateFlow()

    init {
        mainHandler.post {
            getOrCreateView(context)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun getOrCreateView(ctx: Context): View {
        if (webView == null) {
            try {
                webView = WebView(ctx).apply {
                    settings.javaScriptEnabled = true
                    settings.mediaPlaybackRequiresUserGesture = false
                    settings.domStorageEnabled = true
                    settings.userAgentString = "Mozilla/5.0 (Linux; Android 14) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36"
                    webChromeClient = WebChromeClient()
                    webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                        }
                    }
                    settings.databaseEnabled = true
                    addJavascriptInterface(AndroidBridge(), "AndroidBridge")
                    loadDataWithBaseURL("https://www.youtube.com", HTML_TEMPLATE, "text/html", "UTF-8", null)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return webView ?: View(ctx)
    }

    fun playVideo(videoId: String) {
        mainHandler.post {
            if (isPlayerReady && webView != null) {
                webView?.evaluateJavascript("playVideo('$videoId');", null)
            } else {
                pendingVideoId = videoId
                getOrCreateView(context)
            }
        }
    }

    fun pause() {
        mainHandler.post {
            webView?.evaluateJavascript("pauseVideo();", null)
            _isPlaying.value = false
        }
    }

    fun resume() {
        mainHandler.post {
            webView?.evaluateJavascript("resumeVideo();", null)
        }
    }

    fun seekTo(positionMs: Long) {
        val seconds = positionMs / 1000f
        mainHandler.post {
            webView?.evaluateJavascript("seekTo($seconds);", null)
            _currentPositionMs.value = positionMs
        }
    }

    fun setPlaybackRate(rate: Float) {
        mainHandler.post {
            webView?.evaluateJavascript("setPlaybackRate($rate);", null)
        }
    }

    fun stop() {
        mainHandler.post {
            webView?.evaluateJavascript("stopVideo();", null)
            _isPlaying.value = false
        }
    }

    inner class AndroidBridge {
        @JavascriptInterface
        fun onReady() {
            isPlayerReady = true
            mainHandler.post {
                pendingVideoId?.let {
                    playVideo(it)
                    pendingVideoId = null
                }
            }
        }

        @JavascriptInterface
        fun onStateChange(state: Int, playing: Boolean, durationSec: Float) {
            mainHandler.post {
                _isPlaying.value = playing
                if (durationSec > 0) {
                    _durationMs.value = (durationSec * 1000).toLong()
                }
            }
        }

        @JavascriptInterface
        fun onProgress(currentSec: Float, durationSec: Float) {
            mainHandler.post {
                _currentPositionMs.value = (currentSec * 1000).toLong()
                if (durationSec > 0) {
                    _durationMs.value = (durationSec * 1000).toLong()
                }
            }
        }

        @JavascriptInterface
        fun onError(code: Int) {
            mainHandler.post {
                _isPlaying.value = false
            }
        }
    }

    companion object {
        private const val HTML_TEMPLATE = """
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>html,body{margin:0;padding:0;background:#000;width:100%;height:100%;overflow:hidden;}#player{width:100%;height:100%;}</style>
<script src="https://www.youtube.com/iframe_api"></script>
</head>
<body>
<div id="player"></div>
<script>
var player;
var isReady = false;
function onYouTubeIframeAPIReady() {
  player = new YT.Player('player', {
    height: '100%',
    width: '100%',
    playerVars: {
      'autoplay': 1,
      'controls': 0,
      'playsinline': 1,
      'rel': 0,
      'disablekb': 1,
      'fs': 0,
      'iv_load_policy': 3,
      'origin': 'https://www.youtube.com'
    },
    events: {
      'onReady': onPlayerReady,
      'onStateChange': onPlayerStateChange,
      'onError': onPlayerError
    }
  });
}
function onPlayerReady(event) {
  isReady = true;
  if (window.AndroidBridge && window.AndroidBridge.onReady) {
    window.AndroidBridge.onReady();
  }
}
function onPlayerStateChange(event) {
  var isPlaying = (event.data === 1);
  var duration = (player && player.getDuration) ? (player.getDuration() || 0) : 0;
  if (window.AndroidBridge && window.AndroidBridge.onStateChange) {
    window.AndroidBridge.onStateChange(event.data, isPlaying, duration);
  }
}
function onPlayerError(event) {
  if (window.AndroidBridge && window.AndroidBridge.onError) {
    window.AndroidBridge.onError(event.data);
  }
}
function playVideo(videoId) {
  if (player && isReady && player.loadVideoById) {
    player.loadVideoById(videoId);
    player.playVideo();
  }
}
function pauseVideo() {
  if (player && player.pauseVideo) {
    player.pauseVideo();
  }
}
function resumeVideo() {
  if (player && player.playVideo) {
    player.playVideo();
  }
}
function stopVideo() {
  if (player && player.stopVideo) {
    player.stopVideo();
  }
}
function seekTo(seconds) {
  if (player && player.seekTo) {
    player.seekTo(seconds, true);
  }
}
function setPlaybackRate(rate) {
  if (player && player.setPlaybackRate) {
    player.setPlaybackRate(rate);
  }
}
setInterval(function() {
  if (player && isReady && player.getCurrentTime) {
    var c = player.getCurrentTime() || 0;
    var d = player.getDuration() || 0;
    if (window.AndroidBridge && window.AndroidBridge.onProgress) {
      window.AndroidBridge.onProgress(c, d);
    }
  }
}, 500);
</script>
</body>
</html>
        """
    }
}
