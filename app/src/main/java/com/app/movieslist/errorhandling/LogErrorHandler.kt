package com.app.movieslist.errorhandling

import android.util.Log

class LogErrorHandler : ErrorHandler {
    override fun handleError(exception: Exception) {
        Log.e("Error", "An error occurred", exception)
    }
}