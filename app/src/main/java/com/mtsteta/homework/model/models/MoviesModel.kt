package com.mtsteta.homework.model.models

import com.mtsteta.homework.model.features.movies.MoviesDataSource

class MoviesModel(private val moviesDataSource: MoviesDataSource) {
    fun getMovies() = moviesDataSource.getMovies()
}