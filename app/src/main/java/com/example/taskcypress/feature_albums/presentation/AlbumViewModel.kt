package com.example.taskcypress.feature_albums.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.taskcypress.feature_albums.domain.use_cases.GetAlbums
import com.example.taskcypress.feature_albums.domain.use_cases.GetPhotosByAlbum
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val getAlbums: GetAlbums,
    private val getPhotosByAlbum: GetPhotosByAlbum
): ViewModel() {
    val pagedAlbums = getAlbums().cachedIn(viewModelScope)

    fun getPagedPhotos(id: String) = getPhotosByAlbum(id).cachedIn(viewModelScope)
}