package com.example.taskcypress.feature_albums.data.paging

import android.util.Log
import androidx.paging.*
import com.example.taskcypress.core.util.Constants.ALBUM_COUNT
import com.example.taskcypress.feature_albums.data.local.AlbumDao
import com.example.taskcypress.feature_albums.data.local.entities.AlbumEntity
import com.example.taskcypress.feature_albums.data.remote.BackendApi
import com.example.taskcypress.feature_albums.domain.model.Album

class AlbumPagingSource(
    private val api: BackendApi,
    private val dao: AlbumDao
): PagingSource<Int, Album>() {
    override fun getRefreshKey(state: PagingState<Int, Album>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> {
        val position = params.key ?: 1
        val albumCount = dao.getCount()

        try {
            var albums = emptyList<AlbumEntity>()
            if (albumCount < ALBUM_COUNT) {
                Log.d("PagingSource", "load: empty database fetch from network")
                albums = api.getAlbums().map { albumDto ->
                    albumDto.toAlbumEntity()
                }

                if(albums.isNotEmpty()) {
                    dao.clearAllAlbums()
                    dao.insertAlbums(albums = albums)
                }
            }

        } catch (e: Exception) {
            Log.d("TAG", "load: error while loading albums")
        }

        val albums = dao.getAlbumList().map {
            it.toAlbum()
        }

        Log.d("TAG", "load: ${albums.toString()}")

        return LoadResult.Page(
            data = albums,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (albums.isNullOrEmpty()) null else position + 1
        )
    }
}
