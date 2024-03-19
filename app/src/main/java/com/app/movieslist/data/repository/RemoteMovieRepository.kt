package com.app.movieslist.data.repository

import com.app.movieslist.model.Movie
import com.app.movieslist.model.MovieResponse
import com.app.movieslist.network.MovieApiService

class RemoteMovieRepository(private val apiService: MovieApiService) : MovieRepository {
    override suspend fun getMovies(): MovieResponse {
        return apiService.getMovies()
    }

    override suspend fun getMovie(movieID :String): Movie {
        return apiService.getMovie(movieID)
    }
}