package com.example.taskcypress.feature_albums.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskcypress.feature_albums.data.local.entities.AlbumEntity
import com.example.taskcypress.feature_albums.data.local.entities.PhotoEntity

@Database(
    entities = [AlbumEntity::class, PhotoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AlbumDatabase: RoomDatabase() {
    abstract val albumDao: AlbumDao
    abstract val photoDao: PhotoDao
}