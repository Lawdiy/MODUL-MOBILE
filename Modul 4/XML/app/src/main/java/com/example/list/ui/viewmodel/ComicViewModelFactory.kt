package com.example.list.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ComicViewModelFactory(private val appName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ComicViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ComicViewModel(appName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}