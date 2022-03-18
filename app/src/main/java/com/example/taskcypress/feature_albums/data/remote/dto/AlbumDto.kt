package com.example.taskcypress.feature_albums.data.remote.dto

import com.example.taskcypress.feature_albums.data.local.entities.AlbumEntity
import com.example.taskcypress.feature_albums.domain.model.Album

data class AlbumDto(
    val id: Int,
    val title: String,
    val userId: Int
) {
    fun toAlbumEntity(): AlbumEntity {
        return AlbumEntity(id = id)
    }
}