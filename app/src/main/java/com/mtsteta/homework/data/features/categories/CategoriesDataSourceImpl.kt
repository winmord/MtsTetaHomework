package com.mtsteta.homework.data.features.categories

import com.mtsteta.homework.data.dto.CategoryDto

class CategoriesDataSourceImpl: CategoriesDataSource {
    override fun getCategories() = listOf(
        CategoryDto(
            category = "боевики"
        ),
        CategoryDto(
            category = "драмы"
        ),
        CategoryDto(
            category = "комедии"
        ),
        CategoryDto(
            category = "артхаус"
        ),
        CategoryDto(
            category = "мелодрамы"
        ),
        CategoryDto(
            category = "вестерны"
        ),
        CategoryDto(
            category = "военные"
        ),
        CategoryDto(
            category = "детективы"
        ),
        CategoryDto(
            category = "истории"
        ),
        CategoryDto(
            category = "криминал"
        ),
        CategoryDto(
            category = "мультфильмы"
        ),
        CategoryDto(
            category = "ужасы"
        )
    )
}