package com.yrtelf.moviesappkotlinmvvm.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yrtelf.moviesappkotlinmvvm.adapter.MovieListAdapter
import com.yrtelf.moviesappkotlinmvvm.model.Movie
import com.yrtelf.moviesappkotlinmvvm.model.MovieDataSource
import com.yrtelf.moviesappkotlinmvvm.model.OperationCallback

class MovieListViewModel(private val repository: MovieDataSource) : ViewModel() {

    private val _movies =  MutableLiveData<List<Movie>>().apply { value = emptyList() }
    val movies: LiveData<List<Movie>> = _movies

    fun loadMovies(){
        repository.retrieveMovies(object:OperationCallback<Movie>{
                    override fun onSuccess(data: List<Movie>?) {
                        data?.let {
                            _movies.value=it
                        }
                    }

            override fun onError(error: String?) {
                Log.v("Error","asd")
            }

        })
    }

}