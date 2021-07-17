package com.mtsteta.homework.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtsteta.homework.R
import com.mtsteta.homework.data.dto.CategoryDto
import com.mtsteta.homework.data.dto.MovieDto
import com.mtsteta.homework.data.features.categories.CategoriesDataSourceImpl
import com.mtsteta.homework.data.features.movies.MoviesDataSourceImpl
import com.mtsteta.homework.presentation.models.CategoriesModel
import com.mtsteta.homework.presentation.models.MoviesModel
import com.mtsteta.homework.presentation.recyclerviews.adapters.CategoriesRecyclerAdapter
import com.mtsteta.homework.presentation.recyclerviews.adapters.MoviesRecyclerAdapter
import com.mtsteta.homework.presentation.recyclerviews.decorations.BottomSpaceItemDecoration
import com.mtsteta.homework.presentation.recyclerviews.decorations.RightSpaceItemDecoration
import com.mtsteta.homework.presentation.recyclerviews.diffutils.callbacks.CategoriesCallback
import com.mtsteta.homework.presentation.recyclerviews.diffutils.callbacks.MoviesCallback

class MainActivity : AppCompatActivity() {
    private lateinit var moviesModel: MoviesModel
    private lateinit var categoriesModel: CategoriesModel
    private lateinit var categoriesRecyclerView: RecyclerView
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var categoriesAdapter: CategoriesRecyclerAdapter
    private lateinit var moviesAdapter: MoviesRecyclerAdapter
    private var categories = listOf<CategoryDto>()
    private var movies = listOf<MovieDto>()

    private val activityCallbackFunction: (String) -> Unit = { showToast(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        initDataSource()
        setupViews()
        setupDecorations()
        setupLayoutManagers()
        setupAdapters()
        updateData()
    }

    private fun initDataSource() {
        moviesModel = MoviesModel(MoviesDataSourceImpl())
        categoriesModel = CategoriesModel(CategoriesDataSourceImpl())
    }

    private fun setupViews() {
        categoriesRecyclerView = findViewById(R.id.categoriesRecycleView)
        moviesRecyclerView = findViewById(R.id.moviesRecycleView)
    }

    private fun setupDecorations() {
        val rightSpace = resources.getDimension(R.dimen.item_movie_category_end_margin).toInt()
        val rightSpaceItemDecoration = RightSpaceItemDecoration(rightSpace)
        categoriesRecyclerView.addItemDecoration(rightSpaceItemDecoration)

        val bottomSpace = resources.getDimension(R.dimen.item_movie_bottom_margin).toInt()
        val bottomSpaceItemDecoration = BottomSpaceItemDecoration(bottomSpace)
        moviesRecyclerView.addItemDecoration(bottomSpaceItemDecoration)
    }

    private fun setupLayoutManagers() {
        categoriesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val itemWidth = resources.getDimension(R.dimen.item_movie_width).toInt()
        val itemMargin = resources.getDimension(R.dimen.item_movie_end_margin).toInt()
        val moviesRecyclerViewItemWidth = itemWidth + itemMargin
        val moviesRecyclerViewColumnsCount: Int = getGridColumnsCount(moviesRecyclerViewItemWidth)
        moviesRecyclerView.layoutManager = GridLayoutManager(this, moviesRecyclerViewColumnsCount)
    }

    private fun setupAdapters() {
        categoriesAdapter = CategoriesRecyclerAdapter(activityCallbackFunction)
        categoriesRecyclerView.adapter = categoriesAdapter
        moviesAdapter = MoviesRecyclerAdapter(activityCallbackFunction)
        moviesRecyclerView.adapter = moviesAdapter
    }

    private fun updateData() {
        val categoriesCallback = CategoriesCallback(categories, categoriesModel.getCategories())
        val categoriesDiff = DiffUtil.calculateDiff(categoriesCallback)
        categoriesDiff.dispatchUpdatesTo(categoriesAdapter)
        val categories = categoriesModel.getCategories()
        categoriesAdapter.categories = categories

        val moviesCallback = MoviesCallback(movies, moviesModel.getMovies())
        val moviesDiff = DiffUtil.calculateDiff(moviesCallback)
        moviesDiff.dispatchUpdatesTo(moviesAdapter)
        movies = moviesModel.getMovies()
        moviesAdapter.movies = movies
    }

    private fun getGridColumnsCount(itemWidth: Int): Int {
        return (resources.displayMetrics.widthPixels / itemWidth)
    }

    private fun showToast(message: String?) {
        when {
            message.isNullOrEmpty() -> {
                showToast(getString(R.string.main_empty_message))
            }
            else -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}