package com.mtsteta.homework.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mtsteta.homework.model.dto.CategoryDto
import com.mtsteta.homework.model.dto.MovieDto
import com.mtsteta.homework.model.features.categories.CategoriesDataSourceImpl
import com.mtsteta.homework.model.features.movies.MoviesDataSourceImpl
import com.mtsteta.homework.model.models.CategoriesModel
import com.mtsteta.homework.model.models.MoviesModel
import com.mtsteta.homework.view.fragments.HomeFragment
import kotlinx.coroutines.*

typealias HomeViewState = HomeFragment.ViewState

class HomeViewModel : ViewModel() {
    private val moviesModel = MoviesModel(MoviesDataSourceImpl())
    private val categoriesModel = CategoriesModel(CategoriesDataSourceImpl())

    val viewState: LiveData<HomeViewState> get() = _viewState
    private val _viewState = MutableLiveData<HomeViewState>()

    val categoriesDataList: LiveData<List<CategoryDto>> get() = _categoriesDataList
    private val _categoriesDataList = MutableLiveData<List<CategoryDto>>()

    val moviesDataList: LiveData<List<MovieDto>> get() = _moviesDataList
    private val _moviesDataList = MutableLiveData<List<MovieDto>>()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("coroutineException", "handled $exception")
    }

    private fun loadCategoriesData() {
        CoroutineScope(Dispatchers.Main).launch(coroutineExceptionHandler) {
            withContext(Dispatchers.IO) {
                _categoriesDataList.postValue(categoriesModel.getCategories())
            }
        }
    }

    private fun loadMoviesData() {
        CoroutineScope(Dispatchers.Main).launch(coroutineExceptionHandler) {
            withContext(Dispatchers.IO) {
                Thread.sleep(2000)
                _moviesDataList.postValue(moviesModel.getMovies())
                _viewState.postValue(HomeViewState(isRefreshing = false))
            }
        }
    }

    fun loadData() {
        loadCategoriesData()
        loadMoviesData()
    }
}