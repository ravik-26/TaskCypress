package com.example.taskcypress.feature_albums.data.remote

import com.example.taskcypress.feature_albums.data.remote.dto.AlbumDto
import com.example.taskcypress.feature_albums.data.remote.dto.PhotoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface BackendApi {

    @GET("albums")
    suspend fun getAlbums(): List<AlbumDto>

    @GET("photos")
    suspend fun getPhotos(@Query("albumId") albumId: String): List<PhotoDto>
}