package com.army.btsmusic

import android.app.Application
import com.army.btsmusic.bluetooth.ArmyBombBleManager
import com.army.btsmusic.data.local.AppDatabase
import com.army.btsmusic.data.remote.YouTubeMusicService
import com.army.btsmusic.data.repository.DownloadRepository
import com.army.btsmusic.data.repository.MusicRepository
import com.army.btsmusic.player.AudioEngineController
import com.army.btsmusic.social.ListenTogetherManager

class BtsMusicApp : Application() {

    val database: AppDatabase by lazy { AppDatabase.getInstance(this) }
    val youTubeMusicService: YouTubeMusicService by lazy { YouTubeMusicService() }
    val downloadRepository: DownloadRepository by lazy { DownloadRepository(this) }
    val musicRepository: MusicRepository by lazy { MusicRepository(youTubeMusicService, downloadRepository) }
    val audioEngineController: AudioEngineController by lazy { AudioEngineController(this) }
    val armyBombBleManager: ArmyBombBleManager by lazy { ArmyBombBleManager(this) }
    val listenTogetherManager: ListenTogetherManager by lazy { ListenTogetherManager() }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun onTerminate() {
        super.onTerminate()
        try {
            audioEngineController.release()
            armyBombBleManager.disconnect()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    companion object {
        lateinit var instance: BtsMusicApp
            private set
    }
}
