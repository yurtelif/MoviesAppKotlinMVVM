package com.yrtelf.moviesappkotlinmvvm.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yrtelf.moviesappkotlinmvvm.R
import com.yrtelf.moviesappkotlinmvvm.model.MOVIE
import com.yrtelf.moviesappkotlinmvvm.model.Movie
import com.yrtelf.moviesappkotlinmvvm.network.MoviesApi.POSTER_BASE_URL
import com.yrtelf.moviesappkotlinmvvm.ui.MovieDetailActivity

class MovieListAdapter(
    val context: Context,
    private var movies: List<Movie>
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movies_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.textView.setText(movie.title)
        Glide.with(holder.imageView.context).load(POSTER_BASE_URL + movie.poster_path)
            .into(holder.imageView)
        holder.cardMovie.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE, movie)
            context.startActivity(intent)
        }
    }

    fun update(data: List<Movie>) {
        movies = data
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tv_movie_namr)
        val imageView: ImageView = view.findViewById(R.id.iv_movie_photo)
        val cardMovie: ConstraintLayout = view.findViewById(R.id.card_movie)
    }
}