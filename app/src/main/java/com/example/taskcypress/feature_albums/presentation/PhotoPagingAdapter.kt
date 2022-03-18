package com.example.taskcypress.feature_albums.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.taskcypress.R
import com.example.taskcypress.feature_albums.domain.model.Photo
import com.squareup.picasso.Picasso

class PhotoPagingAdapter: PagingDataAdapter<Photo, PhotoPagingAdapter.PhotoViewHolder>(DiffCallback()) {

    class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.photoImageview)
        private val textView: TextView = view.findViewById(R.id.photoTextview)

        fun bind(photo: Photo) {
            Picasso.get().load(photo.thumbnailUrl).into(imageView)
            textView.text = photo.id.toString()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean = oldItem == newItem
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.photo_item, parent, false)
        return PhotoViewHolder(itemView)
    }
}