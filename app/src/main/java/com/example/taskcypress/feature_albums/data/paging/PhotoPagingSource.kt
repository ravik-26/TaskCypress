package com.example.taskcypress.feature_albums.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.taskcypress.core.util.Constants
import com.example.taskcypress.feature_albums.data.local.AlbumDao
import com.example.taskcypress.feature_albums.data.local.PhotoDao
import com.example.taskcypress.feature_albums.data.local.entities.PhotoEntity
import com.example.taskcypress.feature_albums.data.remote.BackendApi
import com.example.taskcypress.feature_albums.domain.model.Photo

class PhotoPagingSource(
    private val api: BackendApi,
    private val dao: PhotoDao,
    private val albumId: String
): PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: 1
        val photoCount = dao.getPhotoCountByAlbum(albumId)

        if (photoCount < Constants.PHOTO_COUNT) {
            Log.d("PagingSource", "load: empty database fetch from network")

            var photos = emptyList<PhotoEntity>()
            try {
                photos = api.getPhotos(albumId).map {
                    it.toPhotoEntity()
                }

            } catch (e: Exception) {
                Log.d("TAG", "load: error while loading photos")
            }

            if(photos.isNotEmpty()) {
                dao.clearPhotoByAlbum(albumId)
                dao.insertPhotos(photos)
            }
        }

        val photos = dao.getPhotosList(albumId).map {
            it.toPhoto()
        }

        Log.d("TAG", "load: ${photos.toString()}")

        return LoadResult.Page(
            data = photos,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (photos.isNullOrEmpty()) null else position + 1
        )
    }
}