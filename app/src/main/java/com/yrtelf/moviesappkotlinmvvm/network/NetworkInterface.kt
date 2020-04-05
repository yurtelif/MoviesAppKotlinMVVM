package com.yrtelf.moviesappkotlinmvvm.network

import com.yrtelf.moviesappkotlinmvvm.model.Movie
import com.yrtelf.moviesappkotlinmvvm.model.MovieResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface NetworkInterface {

    @GET("/3/movie/top_rated?api_key=11459cff1c1ce00e3202addab99f3a91&language=en-us&page=1")
    fun getMovies(): Call<MovieResponse>

}