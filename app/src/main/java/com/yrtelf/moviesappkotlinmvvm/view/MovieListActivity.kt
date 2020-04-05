package com.yrtelf.moviesappkotlinmvvm.view

import android.os.Bundle
import android.util.Log
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
        activityMovieListBinding = DataBindingUtil.setContentView(this,R.layout.activity_movie_list)
        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory()).get(MovieListViewModel::class.java)
        viewModel.loadMovies()

        movieListAdapter = MovieListAdapter(viewModel.movies.value?: emptyList())
        activityMovieListBinding.rvMovies.layoutManager= GridLayoutManager(this,2)
        activityMovieListBinding.rvMovies.adapter = movieListAdapter
        viewModel.movies.observe(this, Observer<List<Movie>> {
            movieListAdapter.update(it)
        })
    }


    /*
    private fun setupViewModel(){
        viewModel = ViewModelProvider(this,Injection.provideViewModelFactory()).get(MuseumViewModel::class.java)
        //viewModel = ViewModelProvider(this,ViewModelFactory(Injection.providerRepository())).get(MuseumViewModel::class.java)
        //viewModel = ViewModelProviders.of(this,ViewModelFactory(Injection.providerRepository())).get(MuseumViewModel::class.java)
        viewModel.museums.observe(this,renderMuseums)

        viewModel.isViewLoading.observe(this,isViewLoadingObserver)
        viewModel.onMessageError.observe(this,onMessageErrorObserver)
        viewModel.isEmptyList.observe(this,emptyListObserver)
    }

    //observers
    private val renderMuseums= Observer<List<Museum>> {
        Log.v(TAG, "data updated $it")
        layoutError.visibility=View.GONE
        layoutEmpty.visibility=View.GONE
        adapter.update(it)
    }
     */
}
