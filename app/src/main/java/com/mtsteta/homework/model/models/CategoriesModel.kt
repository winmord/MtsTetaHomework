package com.mtsteta.homework.model.models

import com.mtsteta.homework.model.features.categories.CategoriesDataSource

class CategoriesModel(private val categoriesDataSource: CategoriesDataSource) {
    fun getCategories() = categoriesDataSource.getCategories()
}