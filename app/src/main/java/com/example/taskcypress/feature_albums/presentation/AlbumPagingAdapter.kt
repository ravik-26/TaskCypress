package com.example.taskcypress.feature_albums.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskcypress.databinding.AlbumItemBinding
import com.example.taskcypress.feature_albums.domain.model.Album
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AlbumPagingAdapter(
    private val viewModel: AlbumViewModel
): PagingDataAdapter<Album, AlbumPagingAdapter.AlbumViewHolder>(DiffCallback()) {

    inner class AlbumViewHolder(private val binding: AlbumItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            ("Album: " + album.id.toString()).also { binding.albumTextview.text = it }

            val photoAdapter = PhotoPagingAdapter()

            binding.childRecyclerview.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL,false)
            binding.childRecyclerview.adapter = photoAdapter
//            binding.childRecyclerview.setHasFixedSize(true)

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.getPagedPhotos(album.id.toString()).collectLatest {
                    photoAdapter.submitData(it)
                }
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean = oldItem == newItem
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(AlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}