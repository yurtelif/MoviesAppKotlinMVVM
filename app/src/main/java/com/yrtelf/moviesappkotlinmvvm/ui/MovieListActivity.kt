package com.yrtelf.moviesappkotlinmvvm.ui

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.yrtelf.moviesappkotlinmvvm.R
import com.yrtelf.moviesappkotlinmvvm.adapter.MovieListAdapter
import com.yrtelf.moviesappkotlinmvvm.databinding.ActivityMovieListBinding
import com.yrtelf.moviesappkotlinmvvm.injection.Injection
import com.yrtelf.moviesappkotlinmvvm.model.Movie
import com.yrtelf.moviesappkotlinmvvm.viewModel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

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

        movieListAdapter = MovieListAdapter(this, ArrayList(viewModel.movies.value))
        rv_movies.layoutManager = GridLayoutManager(this, 2)
        rv_movies.adapter = movieListAdapter
        viewModel.movies.observe(this, Observer<List<Movie>> {
            movieListAdapter.update(ArrayList(it))
        })
        btn_load_more.setOnClickListener { viewModel.loadMovies() }
        performSearchListener()
    }

    private fun performSearchListener() {
        activityMovieListBinding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.length!! > 2) {
                    filter(query)
                }
                return true
            }
        })
    }

    fun filter(text: String) {
        val filteredMovieList: ArrayList<Movie> = ArrayList()
        viewModel.movies.value?.forEach {
            if (it.title.toUpperCase().contains(text.toUpperCase())) {
                filteredMovieList.add(it)
            }
        }
        movieListAdapter.update(filteredMovieList)
    }
}
