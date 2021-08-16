package com.mtsteta.homework.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mtsteta.homework.model.dto.CategoryDto
import com.mtsteta.homework.model.features.preferences.PreferencesDataSourceImpl
import com.mtsteta.homework.model.models.PreferencesModel
import kotlinx.coroutines.*

class ProfileViewModel : ViewModel() {
    private val preferencesModel = PreferencesModel(PreferencesDataSourceImpl())

    val preferencesDataList: LiveData<List<CategoryDto>> get() = _preferencesDataList
    private val _preferencesDataList = MutableLiveData<List<CategoryDto>>()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("coroutineException", "handled $exception")
    }

    private fun loadPreferencesData() {
        CoroutineScope(Dispatchers.Main).launch(coroutineExceptionHandler) {
            withContext(Dispatchers.IO) {
                _preferencesDataList.postValue(preferencesModel.getPreferences())
            }
        }
    }

    fun loadData() {
        loadPreferencesData()
    }
}