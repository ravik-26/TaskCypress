package com.example.taskcypress.feature_albums.domain.repository

import androidx.paging.PagingData
import com.example.taskcypress.core.util.Resource
import com.example.taskcypress.feature_albums.domain.model.Album
import com.example.taskcypress.feature_albums.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun getAlbums(): Flow<PagingData<Album>>
    fun getPhotosByAlbum(albumId: String): Flow<PagingData<Photo>>
}