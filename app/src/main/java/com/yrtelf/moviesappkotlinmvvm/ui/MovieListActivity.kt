package com.yrtelf.moviesappkotlinmvvm.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.yrtelf.moviesappkotlinmvvm.R
import com.yrtelf.moviesappkotlinmvvm.adapter.MovieListAdapter
import com.yrtelf.moviesappkotlinmvvm.base.BaseActivity
import com.yrtelf.moviesappkotlinmvvm.databinding.ActivityMovieListBinding
import com.yrtelf.moviesappkotlinmvvm.injection.Injection
import com.yrtelf.moviesappkotlinmvvm.model.Movie
import com.yrtelf.moviesappkotlinmvvm.viewModel.MovieListViewModel

class MovieListActivity : BaseActivity() {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var activityMovieListBinding: ActivityMovieListBinding
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        activityMovieListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_movie_list)
        viewModel = ViewModelProvider(this, Injection.provideMovieListViewModelFactory()).get(
            MovieListViewModel::class.java
        )
        viewModel.loadMovies()

        movieListAdapter = MovieListAdapter(this, viewModel.movies.value ?: emptyList())
        activityMovieListBinding.rvMovies.layoutManager = GridLayoutManager(this, 2)
        activityMovieListBinding.rvMovies.adapter = movieListAdapter
        viewModel.movies.observe(this, Observer<List<Movie>> {
            movieListAdapter.update(it)
        })
    }
}
