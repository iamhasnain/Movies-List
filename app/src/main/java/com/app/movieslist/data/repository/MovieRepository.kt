package com.app.movieslist.data.repository

import com.app.movieslist.model.Movie
import com.app.movieslist.model.MovieResponse

interface MovieRepository {
    suspend fun getMovies(): MovieResponse
    suspend fun getMovie(movieID :String):Movie
}