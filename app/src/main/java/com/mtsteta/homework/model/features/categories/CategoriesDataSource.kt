package com.mtsteta.homework.model.features.categories

import com.mtsteta.homework.model.dto.CategoryDto

interface CategoriesDataSource {
    fun getCategories(): List<CategoryDto>
}