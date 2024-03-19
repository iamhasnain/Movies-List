package com.app.movieslist.ui.moviesdetails

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.movieslist.R
import com.app.movieslist.data.repository.RemoteMovieRepository
import com.app.movieslist.databinding.ActivityMovieDetailsBinding
import com.app.movieslist.errorhandling.ToastErrorHandler
import com.app.movieslist.model.Movie
import com.app.movieslist.network.RetrofitClient
import com.app.movieslist.ui.movie.MovieListViewModel
import com.app.movieslist.ui.movie.MovieListViewModelFactory
import com.app.movieslist.util.NetworkUtil.saveLastViewedMovie
import com.bumptech.glide.Glide

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var viewModel: MovieDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieRepository = RemoteMovieRepository(RetrofitClient.apiService)
        val errorHandler = ToastErrorHandler(this@MovieDetailsActivity)
        viewModel = ViewModelProvider(this, MovieDetailViewModelFactory(movieRepository, errorHandler))[MovieDetailViewModel::class.java]
        viewModel.movie.observe(this, Observer {
            // Update UI with movies
            if (it != null) {
                saveLastViewedMovie(this@MovieDetailsActivity,it)
                binding.textViewMovieTitle.text = it.title
                binding.textViewReleaseDate.text = it.releaseDate
                binding.textViewOverview.text = it.overview

                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500${it.posterPath}")
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(binding.imageViewMoviePoster)
            }
        })
        val movieId = intent.getLongExtra("movieId",0)
        viewModel.fetcSingleMovie(this@MovieDetailsActivity,movieId.toString())

    }
}