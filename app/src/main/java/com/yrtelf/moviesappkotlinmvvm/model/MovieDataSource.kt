package com.yrtelf.moviesappkotlinmvvm.model

interface MovieDataSource{
    fun retrieveMovies(callback: OperationCallback<Movie>)
    fun cancel()
}

interface OperationCallback<T> {
    fun onSuccess(data:List<T>?)
    fun onError(error:String?)
}