package com.yrtelf.moviesappkotlinmvvm.model

import android.util.Log
import com.google.gson.Gson
import com.yrtelf.moviesappkotlinmvvm.network.MoviesApi
import io.reactivex.disposables.CompositeDisposable
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository : MovieDataSource {

    private var call: Call<MovieResponse>? = null

    override fun retrieveMovies(page: Int,callback: OperationCallback<Movie>) {
        call = MoviesApi.build()?.getMovies(page)
        call?.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                callback.onError(t?.message)
            }

            override fun onResponse(
                call: Call<MovieResponse>?,
                response: Response<MovieResponse>?
            ) {
                response?.body()?.let {
                    callback.onSuccess(response.body()?.movieList)
                }
            }

        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }

}
