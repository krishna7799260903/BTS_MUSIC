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

    lateinit var database: AppDatabase
        private set
    lateinit var youTubeMusicService: YouTubeMusicService
        private set
    lateinit var downloadRepository: DownloadRepository
        private set
    lateinit var musicRepository: MusicRepository
        private set
    lateinit var audioEngineController: AudioEngineController
        private set
    lateinit var armyBombBleManager: ArmyBombBleManager
        private set
    lateinit var listenTogetherManager: ListenTogetherManager
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        database = AppDatabase.getInstance(this)
        youTubeMusicService = YouTubeMusicService()
        downloadRepository = DownloadRepository(this)
        musicRepository = MusicRepository(youTubeMusicService, downloadRepository)
        audioEngineController = AudioEngineController(this)
        armyBombBleManager = ArmyBombBleManager(this)
        listenTogetherManager = ListenTogetherManager()
    }

    override fun onTerminate() {
        super.onTerminate()
        audioEngineController.release()
        armyBombBleManager.disconnect()
    }

    companion object {
        lateinit var instance: BtsMusicApp
            private set
    }
}
