package com.app.movieslist.ui.moviesdetails

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movieslist.data.repository.MovieRepository
import com.app.movieslist.errorhandling.ErrorHandler
import com.app.movieslist.model.Movie
import com.app.movieslist.util.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


class MovieDetailViewModel(private val repository: MovieRepository, private val errorHandler: ErrorHandler) : ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie


    fun fetcSingleMovie(context: Context, id :String) {
        viewModelScope.launch {
            try {
                if (NetworkUtil.isNetworkAvailable(context)) {
                    val movie = withContext(Dispatchers.IO) {
                        repository.getMovie(id)
                    }
                    _movie.value = movie
                } else {
                    val lastViewedMovie = NetworkUtil.getLastViewedMovie(context)
                    if (lastViewedMovie != null) {
                        _movie.value = lastViewedMovie!!
                    } else {
                        handleError(Exception("No internet connection"))
                    }
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