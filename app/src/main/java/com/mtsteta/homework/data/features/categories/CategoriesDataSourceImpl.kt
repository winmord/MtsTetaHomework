package com.mtsteta.homework.data.features.categories

import com.mtsteta.homework.data.dto.CategoryDto

class CategoriesDataSourceImpl: CategoriesDataSource {
    override fun getCategories() : List<CategoryDto> {
        val categories = mutableListOf(
            CategoryDto(
                name = "боевики"
            ),
            CategoryDto(
                name = "драмы"
            ),
            CategoryDto(
                name = "комедии"
            ),
            CategoryDto(
                name = "артхаус"
            ),
            CategoryDto(
                name = "мелодрамы"
            ),
            CategoryDto(
                name = "вестерны"
            ),
            CategoryDto(
                name = "военные"
            ),
            CategoryDto(
                name = "детективы"
            ),
            CategoryDto(
                name = "истории"
            ),
            CategoryDto(
                name = "криминал"
            ),
            CategoryDto(
                name = "мультфильмы"
            ),
            CategoryDto(
                name = "ужасы"
            )
        )

        categories.shuffle()

        return categories
    }
}