package com.mtsteta.homework.data.features.categories

import com.mtsteta.homework.data.dto.CategoryDto

interface CategoriesDataSource {
    fun getCategories(): List<CategoryDto>
}