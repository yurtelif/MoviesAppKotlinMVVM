package com.yrtelf.moviesappkotlinmvvm.injection

import androidx.lifecycle.ViewModelProvider
import com.yrtelf.moviesappkotlinmvvm.model.MovieDataSource
import com.yrtelf.moviesappkotlinmvvm.model.MovieRepository
import com.yrtelf.moviesappkotlinmvvm.viewModel.ViewModelFactory

object Injection {

    private val movieDataSource:MovieDataSource = MovieRepository()
    private val movieViewModelFactory = ViewModelFactory(movieDataSource)

    fun providerRepository():MovieDataSource{
        return movieDataSource
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory{
        return movieViewModelFactory
    }
}