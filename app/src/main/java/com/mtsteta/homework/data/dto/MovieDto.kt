package com.mtsteta.homework.data.dto

data class MovieDto(
    val title: String,
    val description: String,
    val rateScore: Int,
    val ageRestriction: Int,
    val imageUrl: String
)