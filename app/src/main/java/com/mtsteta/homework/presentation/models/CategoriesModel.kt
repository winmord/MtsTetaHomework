package com.mtsteta.homework.presentation.models

import com.mtsteta.homework.data.features.categories.CategoriesDataSource

class CategoriesModel(
    private val categoriesDataSource: CategoriesDataSource
) {
    fun getCategories() = categoriesDataSource.getCategories()
}