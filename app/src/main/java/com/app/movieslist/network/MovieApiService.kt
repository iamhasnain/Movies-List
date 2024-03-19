package com.app.movieslist.network

import com.app.movieslist.model.Movie
import com.app.movieslist.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("/3/discover/movie")
    suspend fun getMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): MovieResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieID: String,
        @Query("language") language: String = "en-US"
    ): Movie
}