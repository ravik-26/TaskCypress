package com.example.taskcypress.feature_albums.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskcypress.feature_albums.data.local.entities.AlbumEntity

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(albums: List<AlbumEntity>)

//    @Query("DELETE FROM albumentity WHERE id=:id")
//    suspend fun deleteAlbumById(id: String)

    @Query("SELECT * FROM albumentity")
    suspend fun getAlbumList(): List<AlbumEntity>

//    @Query("SELECT * FROM albumentity")
//    fun albumPagingSource(): PagingSource<Int, AlbumEntity>

    @Query("DELETE FROM albumentity")
    suspend fun clearAllAlbums()

    @Query("SELECT COUNT(*) FROM albumentity")
    suspend fun getCount(): Int
}