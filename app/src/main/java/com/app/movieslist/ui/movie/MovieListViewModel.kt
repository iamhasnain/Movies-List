package com.app.movieslist.ui.movie

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movieslist.data.repository.MovieRepository
import com.app.movieslist.errorhandling.ErrorHandler
import com.app.movieslist.errorhandling.LogErrorHandler
import com.app.movieslist.model.Movie
import com.app.movieslist.model.MovieResponse
import com.app.movieslist.util.NetworkUtil.getLastViewedMovie
import com.app.movieslist.util.NetworkUtil.isNetworkAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MovieListViewModel(private val repository: MovieRepository,private val errorHandler: ErrorHandler) : ViewModel() {
    private val _movies = MutableLiveData<MovieResponse>()
    val movies: LiveData<MovieResponse> = _movies


    fun fetchMovies(context:Context,) {
        viewModelScope.launch {
            try {
                if (isNetworkAvailable(context)) {
                    val movies = withContext(Dispatchers.IO) {
                        repository.getMovies()
                    }
                    _movies.value = movies
                } else {
                        handleError(Exception("No internet connection"))
                }

            } catch (e: IOException) {
                handleError(e)
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun handleError(exception: Exception) {
        errorHandler.handleError(exception)
    }
}