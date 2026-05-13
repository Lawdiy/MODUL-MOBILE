package com.example.list.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.list.data.model.Comic
import com.example.list.data.source.ComicDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class ComicViewModel(private val appName: String) : ViewModel() {

    private val _comics = MutableStateFlow<List<Comic>>(emptyList())
    val comics: StateFlow<List<Comic>> = _comics.asStateFlow()

    private val _selectedComicId = MutableStateFlow<Int?>(null)
    val selectedComicId: StateFlow<Int?> = _selectedComicId.asStateFlow()

    private val _selectedBrowserUrl = MutableStateFlow<String?>(null)
    val selectedBrowserUrl: StateFlow<String?> = _selectedBrowserUrl.asStateFlow()

    init {
        loadComics()
    }

    private fun loadComics() {
        val data = ComicDataSource.dummyComics
        _comics.value = data
        Timber.d("Data loaded into list: ${data.size} items")
    }

    fun onComicClick(id: Int) {
        Timber.i("Detail button pressed for comic ID: ${id}")

        val currentList = _comics.value
        val foundComic = currentList.find { it.id == id }

        if (foundComic != null) {
            Timber.d("Selected comic data: ${foundComic.title}")
            _selectedComicId.value = foundComic.id
        } else {
            Timber.e("Comic not found in list!")
        }
    }

    fun onBrowserClick(url: String) {
        Timber.i("Explicit Intent (Browser) button pressed for URL: ${url}")
        _selectedBrowserUrl.value = url
    }

    fun onNavigated() {
        _selectedComicId.value = null
        _selectedBrowserUrl.value = null
    }

    fun getAppName() = appName
}