package com.mtsteta.homework.model.features.movies

import com.mtsteta.homework.model.dto.MovieDto

interface MoviesDataSource {
    fun getMovies(): List<MovieDto>
}