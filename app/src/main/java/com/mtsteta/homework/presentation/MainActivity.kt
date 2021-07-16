package com.mtsteta.homework.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtsteta.homework.R
import com.mtsteta.homework.data.dp
import com.mtsteta.homework.data.features.categories.CategoriesDataSourceImpl
import com.mtsteta.homework.data.features.movies.MoviesDataSourceImpl
import com.mtsteta.homework.presentation.models.CategoriesModel
import com.mtsteta.homework.presentation.models.MoviesModel
import com.mtsteta.homework.presentation.recyclerviews.adapters.CategoriesRecyclerAdapter
import com.mtsteta.homework.presentation.recyclerviews.adapters.MoviesRecyclerAdapter
import com.mtsteta.homework.presentation.recyclerviews.decorations.BottomSpaceItemDecoration
import com.mtsteta.homework.presentation.recyclerviews.decorations.RightSpaceItemDecoration
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var moviesModel: MoviesModel
    private lateinit var categoriesModel: CategoriesModel
    private lateinit var categoriesRecyclerView: RecyclerView
    private lateinit var moviesRecyclerView: RecyclerView
    private val callbackFunction: (String) -> Unit = { showToast(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        initDataSource()
        setupViews()
        setupDecorations()
        setupLayoutManagers()
        setupAdapters()
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
        categoriesRecyclerView.addItemDecoration(
            RightSpaceItemDecoration(
                resources.getDimension(R.dimen.item_movie_category_end_margin).toInt().dp
            )
        )
        moviesRecyclerView.addItemDecoration(
            BottomSpaceItemDecoration(
                resources.getDimension(R.dimen.item_movie_bottom_margin).toInt().dp
            )
        )
    }

    private fun setupLayoutManagers() {
        categoriesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val moviesRecyclerViewItemWidth =
            resources.getDimension(R.dimen.item_movie_width).toInt() + resources.getDimension(
                R.dimen.item_movie_end_margin
            ).toInt()

        val moviesRecyclerViewColumnsCount: Int = getGridColumnsCount(moviesRecyclerViewItemWidth)
        moviesRecyclerView.layoutManager = GridLayoutManager(this, moviesRecyclerViewColumnsCount)
    }

    private fun setupAdapters() {
        categoriesRecyclerView.adapter =
            CategoriesRecyclerAdapter(categoriesModel.getCategories(), callbackFunction)
        moviesRecyclerView.adapter =
            MoviesRecyclerAdapter(moviesModel.getMovies(), callbackFunction)
    }

    private fun getGridColumnsCount(itemWidth: Int): Int {
        val density = resources.displayMetrics.density
        val dpWidth: Float = resources.displayMetrics.widthPixels / density
        return (dpWidth / itemWidth.dp).roundToInt()
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