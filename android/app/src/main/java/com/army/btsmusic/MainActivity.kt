package com.army.btsmusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.army.btsmusic.bluetooth.ArmyBombBleManager
import com.army.btsmusic.data.remote.YouTubeMusicService
import com.army.btsmusic.data.repository.DownloadRepository
import com.army.btsmusic.data.repository.MusicRepository
import com.army.btsmusic.player.AudioEngineController
import com.army.btsmusic.social.ListenTogetherManager
import com.army.btsmusic.theme.BtsMusicTheme
import com.army.btsmusic.theme.DarkBackground
import com.army.btsmusic.ui.navigation.BtsNavigation
import com.army.btsmusic.viewmodel.ArmyBombViewModel
import com.army.btsmusic.viewmodel.BoraLiveViewModel
import com.army.btsmusic.viewmodel.LibraryViewModel
import com.army.btsmusic.viewmodel.MusicPlayerViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            enableEdgeToEdge()
        } catch (e: Throwable) {
            e.printStackTrace()
        }

        val app = application as? BtsMusicApp
        val ytService = app?.youTubeMusicService ?: YouTubeMusicService()
        val dlRepo = app?.downloadRepository ?: DownloadRepository(this)
        val musicRepo = app?.musicRepository ?: MusicRepository(ytService, dlRepo)
        val audioEngine = app?.audioEngineController ?: AudioEngineController(this)
        val bleManager = app?.armyBombBleManager ?: ArmyBombBleManager(this)
        val listenTogetherManager = app?.listenTogetherManager ?: ListenTogetherManager()

        val playerViewModel = MusicPlayerViewModel(audioEngine)
        val libraryViewModel = LibraryViewModel(musicRepo, dlRepo, ytService)
        val boraLiveViewModel = BoraLiveViewModel()
        val armyBombViewModel = ArmyBombViewModel(bleManager)

        setContent {
            BtsMusicTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DarkBackground
                ) {
                    BtsNavigation(
                        playerViewModel = playerViewModel,
                        libraryViewModel = libraryViewModel,
                        boraLiveViewModel = boraLiveViewModel,
                        armyBombViewModel = armyBombViewModel,
                        listenTogetherManager = listenTogetherManager
                    )
                }
            }
        }
    }
}
