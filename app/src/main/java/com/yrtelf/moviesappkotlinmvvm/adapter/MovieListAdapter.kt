package com.yrtelf.moviesappkotlinmvvm.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yrtelf.moviesappkotlinmvvm.R
import com.yrtelf.moviesappkotlinmvvm.model.MOVIE
import com.yrtelf.moviesappkotlinmvvm.model.Movie
import com.yrtelf.moviesappkotlinmvvm.network.MoviesApi.POSTER_BASE_URL
import com.yrtelf.moviesappkotlinmvvm.ui.MovieDetailActivity

class MovieListAdapter(
    val context: Context,
    private var movies: ArrayList<Movie>
) : PagedListAdapter<Movie, RecyclerView.ViewHolder>(MoviesDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movies_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = movies[position]
        (holder as ViewHolder).bind(movie, context)
    }


    fun update(data: ArrayList<Movie>, fromFilter: Boolean) {
        if(fromFilter){
            movies = data
        } else {
            movies.addAll(data)
        }
        notifyDataSetChanged()
    }

    fun filter(text: String) {
        val filteredMovieList: ArrayList<Movie> = ArrayList()
        movies.forEach {
            if (it.title.toUpperCase().contains(text.toUpperCase())) {
                filteredMovieList.add(it)
            }
        }
        update(filteredMovieList,true)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tv_movie_namr)
        val imageView: ImageView = view.findViewById(R.id.iv_movie_photo)
        val cardMovie: ConstraintLayout = view.findViewById(R.id.card_movie)
        fun bind(movie: Movie?, context: Context) {
            textView.setText(movie?.title)
            Glide.with(imageView.context).load(POSTER_BASE_URL + movie?.poster_path)
                .into(imageView)
            cardMovie.setOnClickListener {
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra(MOVIE, movie)
                context.startActivity(intent)
            }
        }
    }

    class MoviesDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}