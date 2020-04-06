package com.yrtelf.moviesappkotlinmvvm.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yrtelf.moviesappkotlinmvvm.model.Movie

class MovieDetailViewModel() : ViewModel() {

    var movie: Movie? = Movie("", "", "", "", "",0)
}