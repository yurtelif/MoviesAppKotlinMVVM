package com.yrtelf.moviesappkotlinmvvm.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesApi {

    const val BASE_URL = "https://api.themoviedb.org"
    const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"

    private var networkInterface: NetworkInterface? = null

    fun build(): NetworkInterface? {
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        networkInterface = retrofit.create(NetworkInterface::class.java)
        return networkInterface as NetworkInterface
    }

    fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}
