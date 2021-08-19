package com.mtsteta.homework.model.models

import com.mtsteta.homework.model.features.preferences.PreferencesDataSource

class PreferencesModel(private val preferencesDataSource: PreferencesDataSource) {
    fun getPreferences() = preferencesDataSource.getCategories()
}