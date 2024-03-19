package com.app.movieslist.ui.moviesdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.movieslist.data.repository.MovieRepository
import com.app.movieslist.errorhandling.ErrorHandler
import com.app.movieslist.ui.movie.MovieListViewModel

class MovieDetailViewModelFactory(
    private val movieRepository: MovieRepository,
    private val errorHandler: ErrorHandler
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(movieRepository, errorHandler) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}