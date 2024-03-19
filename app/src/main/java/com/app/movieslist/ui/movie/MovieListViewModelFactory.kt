package com.app.movieslist.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.movieslist.data.repository.MovieRepository
import com.app.movieslist.errorhandling.ErrorHandler

class MovieListViewModelFactory(
    private val movieRepository: MovieRepository,
    private val errorHandler: ErrorHandler
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(movieRepository, errorHandler) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}