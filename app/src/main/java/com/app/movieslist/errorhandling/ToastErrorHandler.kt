package com.app.movieslist.errorhandling

import android.content.Context
import android.widget.Toast
import java.io.IOException

class ToastErrorHandler(private val context: Context) : ErrorHandler {
    override fun handleError(exception: Exception) {
        val errorMessage = when (exception) {
            is IOException -> "Network error occurred"
            else -> "An error occurred: ${exception.message}"
        }
        showToast(errorMessage)
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}