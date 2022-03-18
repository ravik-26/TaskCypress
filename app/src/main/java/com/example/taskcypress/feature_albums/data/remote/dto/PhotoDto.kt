package com.example.taskcypress.feature_albums.data.remote.dto

import com.example.taskcypress.feature_albums.data.local.entities.PhotoEntity
import com.example.taskcypress.feature_albums.domain.model.Photo

data class PhotoDto(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
) {
    // mapper function
    fun toPhotoEntity(): PhotoEntity {
        return PhotoEntity(
            albumId = albumId,
            id = id,
            thumbnailUrl = thumbnailUrl,
            url = url
        )
    }
}