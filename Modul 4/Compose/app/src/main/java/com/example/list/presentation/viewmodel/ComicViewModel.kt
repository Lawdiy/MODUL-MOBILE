package com.example.list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.list.data.ComicDataSource
import com.example.list.domain.model.Comic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class ComicViewModel(private val category: String) : ViewModel() {

    private val _comics = MutableStateFlow<List<Comic>>(emptyList())
    val comics: StateFlow<List<Comic>> = _comics.asStateFlow()

    private val _navigateToDetail = MutableStateFlow<Int?>(null)
    val navigateToDetail: StateFlow<Int?> = _navigateToDetail.asStateFlow()

    init {
        loadComics()
    }

    private fun loadComics() {
        if (category == "All") {
            _comics.value = ComicDataSource.dummyComics
            Timber.d("Komik yang masuk ke dalam list: ${_comics.value.size}")
        }
    }

    fun onComicClicked(comicId: Int) {
        val selectedComic = _comics.value.find { it.id == comicId }
        Timber.d(
            "Log data dari list yang dipilih ketika berpindah ke halaman Detail: ${selectedComic?.title}"
        )
        _navigateToDetail.value = comicId
    }

    fun onNavigated() {
        _navigateToDetail.value = null
    }
}

class ComicViewModelFactory(private val category: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ComicViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ComicViewModel(category) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}