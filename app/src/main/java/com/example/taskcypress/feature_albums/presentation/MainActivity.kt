package com.example.taskcypress.feature_albums.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskcypress.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.DividerItemDecoration




@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: AlbumViewModel by viewModels()
    private val albumPagingAdapter by lazy { AlbumPagingAdapter(viewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lm = LinearLayoutManager(this)

        binding.albumRecyclerView.apply {
            adapter = albumPagingAdapter
            layoutManager = lm
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)
        }

        viewModel.viewModelScope.launch {
            viewModel.pagedAlbums.collectLatest { source ->
                albumPagingAdapter.submitData(source)
                Log.d("TAG", "onCreate: ${source.toString()}")
            }
        }
    }
}