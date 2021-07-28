package com.mtsteta.homework.presentation.models

import com.mtsteta.homework.data.features.movies.MoviesDataSource

class MoviesModel(private val moviesDataSource: MoviesDataSource) {
    fun getMovies() = moviesDataSource.getMovies()
}