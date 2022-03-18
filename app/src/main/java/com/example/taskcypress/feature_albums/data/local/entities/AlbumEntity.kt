package com.example.taskcypress.feature_albums.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskcypress.feature_albums.domain.model.Album

@Entity
data class AlbumEntity(
    @PrimaryKey
    val id: Int
) {
    fun toAlbum(): Album {
        return Album(id = id)
    }
}
