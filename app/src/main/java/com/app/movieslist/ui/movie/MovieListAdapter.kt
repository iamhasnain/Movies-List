package com.app.movieslist.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.app.movieslist.R
import com.app.movieslist.model.Movie

class MovieListAdapter() : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    private var filteredMovies: List<Movie> = emptyList()
    private var movies : List<Movie> = emptyList()
    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = filteredMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = filteredMovies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val movieTitleTextView: TextView = itemView.findViewById(R.id.text_view_movie_title)
        private val releaseDateTextView: TextView = itemView.findViewById(R.id.text_view_movie_release_date)
        private val overviewTextView: TextView = itemView.findViewById(R.id.text_view_movie_overview)
        private val moviePosterImageView: ImageView = itemView.findViewById(R.id.image_view_movie_poster)

        init {
            itemView.setOnClickListener(this)
        }
        fun bind(movie: Movie) {
            movieTitleTextView.text = movie.title
            releaseDateTextView.text = movie.releaseDate
            overviewTextView.text = movie.overview

            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(moviePosterImageView)
        }

        override fun onClick(view: View?) {
            listener?.onItemClick(adapterPosition,filteredMovies[adapterPosition])
        }
    }

    fun filterMovies(query: String) {
        filteredMovies = if (query.isBlank()) {
            movies
        } else {
            movies.filter { it.title.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }

    fun setData(movies: List<Movie>) {
        this.filteredMovies = movies
        this.movies = movies
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int,movie: Movie)
    }
}
