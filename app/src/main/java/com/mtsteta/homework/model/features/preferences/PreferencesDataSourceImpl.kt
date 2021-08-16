package com.mtsteta.homework.model.features.preferences

import com.mtsteta.homework.model.dto.CategoryDto
import com.mtsteta.homework.model.features.preferences.PreferencesDataSource

class PreferencesDataSourceImpl : PreferencesDataSource {
    override fun getPreferences() = mutableListOf(
        CategoryDto(
            name = "боевики"
        ),
        CategoryDto(
            name = "драмы"
        ),
        CategoryDto(
            name = "комедии"
        )
    )
}