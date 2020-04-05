package com.yrtelf.moviesappkotlinmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.yrtelf.moviesappkotlinmvvm.R
import com.yrtelf.moviesappkotlinmvvm.databinding.ActivityMovieDetailBinding
import com.yrtelf.moviesappkotlinmvvm.injection.Injection
import com.yrtelf.moviesappkotlinmvvm.model.MOVIE
import com.yrtelf.moviesappkotlinmvvm.model.Movie
import com.yrtelf.moviesappkotlinmvvm.viewModel.MovieDetailViewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var activityMovieDetailBinding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movie = intent.getSerializableExtra(MOVIE) as? Movie
        activityMovieDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        viewModel = ViewModelProvider(this, Injection.provideMovieDetailViewModelFactory()).get(
            MovieDetailViewModel::class.java
        )
        viewModel.movie = movie
        activityMovieDetailBinding.viewModel = viewModel

    }


}
