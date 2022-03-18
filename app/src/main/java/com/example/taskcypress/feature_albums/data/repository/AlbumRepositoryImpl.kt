package com.example.taskcypress.feature_albums.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.taskcypress.core.util.Resource
import com.example.taskcypress.feature_albums.data.local.AlbumDao
import com.example.taskcypress.feature_albums.data.local.AlbumDatabase
import com.example.taskcypress.feature_albums.data.paging.AlbumPagingSource
import com.example.taskcypress.feature_albums.data.paging.PhotoPagingSource
import com.example.taskcypress.feature_albums.data.remote.BackendApi
import com.example.taskcypress.feature_albums.domain.model.Album
import com.example.taskcypress.feature_albums.domain.model.Photo
import com.example.taskcypress.feature_albums.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AlbumRepositoryImpl(
    private val api: BackendApi,
    private val db: AlbumDatabase
): AlbumRepository {
    override fun getAlbums(): Flow<PagingData<Album>> {
        return Pager(PagingConfig(
            pageSize = 10,
            enablePlaceholders = true
        )) {
            AlbumPagingSource(api, db.albumDao)
        }.flow
    }

    override fun getPhotosByAlbum(albumId: String): Flow<PagingData<Photo>> {
        return Pager(PagingConfig(pageSize = 10, enablePlaceholders = true)){
            PhotoPagingSource(api, db.photoDao, albumId)
        }.flow
    }
}