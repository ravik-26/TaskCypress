package com.example.taskcypress.feature_albums.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskcypress.feature_albums.domain.model.Photo

@Entity
data class PhotoEntity(
    val albumId: Int,
    @PrimaryKey
    val id: Int,
    val thumbnailUrl: String,
    val url: String
) {
    fun toPhoto(): Photo {
        return Photo(
            albumId = albumId,
            id = id,
            thumbnailUrl = thumbnailUrl,
            url = url
        )
    }
}
