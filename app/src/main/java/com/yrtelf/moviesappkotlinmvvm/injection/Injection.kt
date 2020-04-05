package com.yrtelf.moviesappkotlinmvvm.injection

import androidx.lifecycle.ViewModelProvider
import com.yrtelf.moviesappkotlinmvvm.model.Movie
import com.yrtelf.moviesappkotlinmvvm.model.MovieDataSource
import com.yrtelf.moviesappkotlinmvvm.model.MovieRepository
import com.yrtelf.moviesappkotlinmvvm.viewModel.MovieDetailViewModelFactory
import com.yrtelf.moviesappkotlinmvvm.viewModel.MovieListViewModelFactory

object Injection {

    private val movieDataSource: MovieDataSource = MovieRepository()
    private val movieListViewModelFactory = MovieListViewModelFactory(movieDataSource)
    private val movieDetailViewModelFactory = MovieDetailViewModelFactory()

    fun provideMovieListViewModelFactory(): ViewModelProvider.Factory {
        return movieListViewModelFactory
    }

    fun provideMovieDetailViewModelFactory(): ViewModelProvider.Factory {
        return movieDetailViewModelFactory
    }

}

