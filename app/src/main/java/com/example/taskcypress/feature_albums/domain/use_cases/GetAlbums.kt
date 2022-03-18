package com.example.taskcypress.feature_albums.domain.use_cases

import androidx.paging.PagingData
import com.example.taskcypress.feature_albums.domain.model.Album
import com.example.taskcypress.feature_albums.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow

class GetAlbums(
    private val repository: AlbumRepository
) {
    operator fun invoke(): Flow<PagingData<Album>> {
        return repository.getAlbums()
    }
}