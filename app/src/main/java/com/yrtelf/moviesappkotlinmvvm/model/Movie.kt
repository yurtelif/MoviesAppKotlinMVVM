package com.yrtelf.moviesappkotlinmvvm.model

import java.io.Serializable

data class Movie(
    val title: String,
    val poster_path: String,
    val overview: String,
    val popularity: String,
    val vote_average: String
) : Serializable

const val MOVIE = "movie"