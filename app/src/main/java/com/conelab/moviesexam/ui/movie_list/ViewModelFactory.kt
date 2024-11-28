package com.conelab.moviesexam.ui.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.conelab.moviesexam.data.api.MoviesRepository

class MoviesViewModelFactory(
    private val repository: MoviesRepository // Ejemplo de dependencia requerida
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MoviesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
