package com.army.btsmusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
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
        enableEdgeToEdge()

        val app = application as BtsMusicApp
        val playerViewModel = MusicPlayerViewModel(app.audioEngineController)
        val libraryViewModel = LibraryViewModel(app.musicRepository, app.downloadRepository, app.youTubeMusicService)
        val boraLiveViewModel = BoraLiveViewModel()
        val armyBombViewModel = ArmyBombViewModel(app.armyBombBleManager)
        val listenTogetherManager = app.listenTogetherManager

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
