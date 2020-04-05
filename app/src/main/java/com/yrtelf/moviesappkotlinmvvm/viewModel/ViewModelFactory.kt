package com.yrtelf.moviesappkotlinmvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yrtelf.moviesappkotlinmvvm.model.MovieDataSource

class ViewModelFactory(private val repository:MovieDataSource):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(repository) as T
    }
}