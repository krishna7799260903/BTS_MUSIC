package com.army.btsmusic.data.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AppDatabase private constructor(context: Context) : SQLiteOpenHelper(
    context.applicationContext,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    private val _tracksFlow = MutableStateFlow<List<DownloadEntity>>(emptyList())

    val trackDao: TrackDao = object : TrackDao {
        override fun getAllDownloadedTracks(): Flow<List<DownloadEntity>> = _tracksFlow.asStateFlow()

        override suspend fun getDownloadById(trackId: String): DownloadEntity? = withContext(Dispatchers.IO) {
            val db = readableDatabase
            var entity: DownloadEntity? = null
            db.query(
                TABLE_NAME,
                null,
                "$COLUMN_ID = ?",
                arrayOf(trackId),
                null,
                null,
                null
            ).use { cursor ->
                if (cursor.moveToFirst()) {
                    entity = cursorToEntity(cursor)
                }
            }
            entity
        }

        override fun isTrackDownloaded(trackId: String): Flow<Boolean> {
            return _tracksFlow.map { list -> list.any { it.id == trackId } }
        }

        override suspend fun insertDownloadedTrack(track: DownloadEntity) = withContext(Dispatchers.IO) {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_ID, track.id)
                put(COLUMN_TITLE, track.title)
                put(COLUMN_ARTIST, track.artist)
                put(COLUMN_MEMBER, track.memberStageName)
                put(COLUMN_ALBUM, track.album)
                put(COLUMN_DURATION, track.durationSeconds)
                put(COLUMN_YT_ID, track.youtubeVideoId)
                put(COLUMN_COVER_URL, track.coverUrl)
                put(COLUMN_FILE_PATH, track.localFilePath)
                put(COLUMN_DOWNLOADED_AT, track.downloadedAt)
                put(COLUMN_SIZE_BYTES, track.fileSizeBytes)
            }
            db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE)
            refreshCache()
        }

        override suspend fun deleteDownloadedTrack(trackId: String) = withContext(Dispatchers.IO) {
            val db = writableDatabase
            db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(trackId))
            refreshCache()
        }

        override suspend fun clearAllDownloads() = withContext(Dispatchers.IO) {
            val db = writableDatabase
            db.delete(TABLE_NAME, null, null)
            refreshCache()
        }
    }

    fun trackDao(): TrackDao = trackDao

    init {
        refreshCache()
    }

    private fun refreshCache() {
        try {
            val db = readableDatabase
            val list = mutableListOf<DownloadEntity>()
            db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                "$COLUMN_DOWNLOADED_AT DESC"
            ).use { cursor ->
                while (cursor.moveToNext()) {
                    list.add(cursorToEntity(cursor))
                }
            }
            _tracksFlow.value = list
        } catch (e: Exception) {
            _tracksFlow.value = emptyList()
        }
    }

    private fun cursorToEntity(cursor: Cursor): DownloadEntity {
        return DownloadEntity(
            id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID)),
            title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
            artist = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ARTIST)),
            memberStageName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MEMBER)),
            album = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ALBUM)),
            durationSeconds = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DURATION)),
            youtubeVideoId = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_YT_ID)),
            coverUrl = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COVER_URL)),
            localFilePath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FILE_PATH)),
            downloadedAt = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DOWNLOADED_AT)),
            fileSizeBytes = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_SIZE_BYTES))
        )
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS $TABLE_NAME (
                $COLUMN_ID TEXT PRIMARY KEY,
                $COLUMN_TITLE TEXT NOT NULL,
                $COLUMN_ARTIST TEXT NOT NULL,
                $COLUMN_MEMBER TEXT NOT NULL,
                $COLUMN_ALBUM TEXT NOT NULL,
                $COLUMN_DURATION INTEGER NOT NULL,
                $COLUMN_YT_ID TEXT NOT NULL,
                $COLUMN_COVER_URL TEXT NOT NULL,
                $COLUMN_FILE_PATH TEXT NOT NULL,
                $COLUMN_DOWNLOADED_AT INTEGER NOT NULL,
                $COLUMN_SIZE_BYTES INTEGER NOT NULL
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "bts_music_database.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "downloaded_tracks"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_ARTIST = "artist"
        private const val COLUMN_MEMBER = "memberStageName"
        private const val COLUMN_ALBUM = "album"
        private const val COLUMN_DURATION = "durationSeconds"
        private const val COLUMN_YT_ID = "youtubeVideoId"
        private const val COLUMN_COVER_URL = "coverUrl"
        private const val COLUMN_FILE_PATH = "localFilePath"
        private const val COLUMN_DOWNLOADED_AT = "downloadedAt"
        private const val COLUMN_SIZE_BYTES = "fileSizeBytes"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AppDatabase(context).also { INSTANCE = it }
            }
        }
    }
}
