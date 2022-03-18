package com.example.taskcypress.feature_albums.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskcypress.feature_albums.data.local.entities.PhotoEntity

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photos: List<PhotoEntity>)

    @Query("SELECT * FROM photoentity WHERE albumId=:id")
    suspend fun getPhotosList(id: String): List<PhotoEntity>

    @Query("DELETE FROM photoentity WHERE albumId=:id")
    suspend fun clearPhotoByAlbum(id: String)

    @Query("SELECT COUNT(*) FROM photoentity WHERE albumId=:id")
    suspend fun getPhotoCountByAlbum(id: String): Int
}