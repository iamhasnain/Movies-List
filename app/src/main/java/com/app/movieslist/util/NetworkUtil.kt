package com.app.movieslist.util

import android.content.Context
import android.net.ConnectivityManager
import com.app.movieslist.model.Movie

object NetworkUtil {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun saveLastViewedMovie(context: Context, movie: Movie) {
        val sharedPreferences = context.getSharedPreferences("LastViewedMovie", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putLong("id", movie.id)
            putString("title", movie.title)
            putString("overview", movie.overview)
            putString("releaseDate", movie.releaseDate)
            putString("posterPath", movie.posterPath)


            putBoolean("adult", movie.adult)
            putString("backdrop_path", movie.backdropPath)
            putLong("id",movie.id)
            putString("original_language", movie.originalLanguage)
            putString("original_title", movie.originalTitle)
            putString("overview", movie.overview)
            putFloat("popularity", movie.popularity.toFloat())
            putString("poster_path", movie.posterPath)
            putString("release_date", movie.releaseDate)
            putString("title", movie.title)
            putBoolean("video", movie.video)
            putFloat("vote_average", movie.voteAverage.toFloat())
            putInt("vote_count", movie.voteCount)

            apply()
        }
    }

    fun getLastViewedMovie(context: Context): Movie? {
        val sharedPreferences = context.getSharedPreferences("LastViewedMovie", Context.MODE_PRIVATE)
        val adult = sharedPreferences.getBoolean("adult", false)
        val backdrop_path = sharedPreferences.getString("backdrop_path", "")
        val id = sharedPreferences.getLong("id",0)
        val original_language = sharedPreferences.getString("original_language", "")
        val original_title = sharedPreferences.getString("original_title", "")

        val overview = sharedPreferences.getString("overview", "")
        val popularity = sharedPreferences.getFloat("popularity", 0f)
        val poster_path = sharedPreferences.getString("poster_path", "")
        val release_date = sharedPreferences.getString("release_date", "")
        val title = sharedPreferences.getString("title", "")
        val video = sharedPreferences.getBoolean("video", false)
        val vote_average = sharedPreferences.getFloat("vote_average", 0f)
        val vote_count = sharedPreferences.getInt("vote_count", 0)

        return if (title.isNullOrEmpty()) {
            null
        } else {
            Movie(adult,backdrop_path,id,original_language!!,original_title!!
                    , overview!!,popularity.toDouble(),poster_path,release_date!!,title,video, vote_average.toDouble(),vote_count)
        }
    }
}