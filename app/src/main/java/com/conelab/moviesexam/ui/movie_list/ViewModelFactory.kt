package com.conelab.moviesexam.ui.movie_list

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.conelab.moviesexam.data.api.MoviesRepository

class MoviesViewModelFactory(
    private val repository: MoviesRepository,
    private val context: Context
) : ViewModelProvider.Factory {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MoviesViewModel(repository, context) as T // Pasamos el contexto aquí
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
