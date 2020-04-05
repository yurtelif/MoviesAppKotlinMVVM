package com.yrtelf.moviesappkotlinmvvm.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yrtelf.moviesappkotlinmvvm.network.MoviesApi.POSTER_BASE_URL

object CustomViewBindings {

    @BindingAdapter("setAdapter")
    @JvmStatic
    fun bindRecyclerViewAdapter(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<*>?
    ) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
    }

    @BindingAdapter("setImageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(POSTER_BASE_URL + imageUrl).apply(RequestOptions().optionalCenterCrop())
            .into(view)
    }
}
