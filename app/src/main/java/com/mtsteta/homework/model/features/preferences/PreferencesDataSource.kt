package com.mtsteta.homework.model.features.preferences

import com.mtsteta.homework.model.dto.CategoryDto

interface PreferencesDataSource {
    fun getCategories(): List<CategoryDto>
}