package com.yrtelf.moviesappkotlinmvvm.model

interface MovieDataSource{
    fun retrieveMovies(page: Int, callback: OperationCallback<Movie>)
    fun cancel()
}

interface OperationCallback<T> {
    fun onSuccess(data:List<T>?)
    fun onError(error:String?)
}