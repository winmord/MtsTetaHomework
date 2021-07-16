package com.mtsteta.homework.data.features.movies

import com.mtsteta.homework.data.dto.MovieDto

interface MoviesDataSource {
    fun getMovies(): List<MovieDto>
}