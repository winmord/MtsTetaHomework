package com.mtsteta.homework.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mtsteta.homework.R
import com.mtsteta.homework.model.dto.CategoryDto
import com.mtsteta.homework.model.dto.MovieDto
import com.mtsteta.homework.model.features.categories.CategoriesDataSourceImpl
import com.mtsteta.homework.model.features.movies.MoviesDataSourceImpl
import com.mtsteta.homework.model.models.CategoriesModel
import com.mtsteta.homework.model.models.MoviesModel
import com.mtsteta.homework.view.recyclerviews.adapters.CategoriesRecyclerAdapter
import com.mtsteta.homework.view.recyclerviews.adapters.MoviesRecyclerAdapter
import com.mtsteta.homework.view.recyclerviews.decorations.BottomSpaceItemDecoration
import com.mtsteta.homework.view.recyclerviews.decorations.RightSpaceItemDecoration
import kotlinx.coroutines.*

class HomeFragment : Fragment() {
    private lateinit var moviesModel: MoviesModel
    private lateinit var categoriesModel: CategoriesModel
    private var categoriesRecyclerView: RecyclerView? = null
    private var moviesRecyclerView: RecyclerView? = null
    private var homeSwipeRefreshLayout: SwipeRefreshLayout? = null
    private lateinit var categoriesLayoutManager: LinearLayoutManager
    private lateinit var moviesLayoutManager: StaggeredGridLayoutManager
    private lateinit var categoriesAdapter: CategoriesRecyclerAdapter
    private lateinit var moviesAdapter: MoviesRecyclerAdapter
    private var categories = listOf<CategoryDto>()
    private var movies = listOf<MovieDto>()
    private val activityCallbackFunction: (String) -> Unit = { showToast(it) }
    private val moviesCallbackFunction: (MovieDto) -> Unit = {
        val bundle = bundleOf(MOVIE_DTO_BUNDLE_KEY to it)
        view?.findNavController()?.navigate(R.id.movieDetailsFragment, bundle)
    }
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("coroutineException", "handled $exception")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData(view)
    }

    private fun loadData(view: View) {
        initDataSource()
        setupViews(view)
        setupListeners()
        setupDecorations()
        setupLayoutManagers()
        updateLayoutManagers()
        setupAdapters()
        setupAdaptersData()
        updateData()
    }

    private fun initDataSource() {
        moviesModel = MoviesModel(MoviesDataSourceImpl())
        categoriesModel = CategoriesModel(CategoriesDataSourceImpl())
    }

    private fun setupViews(view: View?) {
        categoriesRecyclerView = view?.findViewById(R.id.rvCategories)
        moviesRecyclerView = view?.findViewById(R.id.rvMovies)
        homeSwipeRefreshLayout = view?.findViewById(R.id.srlMoviesList)
    }

    private fun setupListeners() {
        homeSwipeRefreshLayout?.setOnRefreshListener {
            updateMoviesData()
        }
    }

    private fun setupDecorations() {
        val rightSpace = resources.getDimension(R.dimen.item_movie_category_end_margin).toInt()
        val rightSpaceItemDecoration = RightSpaceItemDecoration(rightSpace)
        categoriesRecyclerView?.addItemDecoration(rightSpaceItemDecoration)

        val bottomSpace = resources.getDimension(R.dimen.item_movie_bottom_margin).toInt()
        val bottomSpaceItemDecoration = BottomSpaceItemDecoration(bottomSpace)
        moviesRecyclerView?.addItemDecoration(bottomSpaceItemDecoration)
    }

    private fun setupLayoutManagers() {
        categoriesLayoutManager =
            LinearLayoutManager(view?.context, LinearLayoutManager.HORIZONTAL, false)

        val itemWidth = resources.getDimension(R.dimen.item_movie_width).toInt()
        val itemMargin = resources.getDimension(R.dimen.item_movie_end_margin).toInt()
        val moviesRecyclerViewItemWidth = itemWidth + itemMargin
        val moviesSpanCount = getGridColumnsCount(moviesRecyclerViewItemWidth)

        moviesLayoutManager =
            StaggeredGridLayoutManager(moviesSpanCount, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun updateLayoutManagers() {
        categoriesRecyclerView?.layoutManager = categoriesLayoutManager
        moviesRecyclerView?.layoutManager = moviesLayoutManager
    }

    private fun setupAdapters() {
        categoriesAdapter = CategoriesRecyclerAdapter(activityCallbackFunction)
        categoriesRecyclerView?.adapter = categoriesAdapter

        moviesAdapter = MoviesRecyclerAdapter(moviesCallbackFunction)
        moviesRecyclerView?.adapter = moviesAdapter
    }

    private fun setupAdaptersData() {
        categoriesAdapter.categories = categories
        moviesAdapter.movies = movies
    }

    private fun updateCategoriesData() {
        CoroutineScope(Dispatchers.Main).launch(coroutineExceptionHandler) {
            withContext(Dispatchers.IO) {
                categories = categoriesModel.getCategories()
            }

            updateCategories(categories)
        }
    }

    private fun updateMoviesData() {
        CoroutineScope(Dispatchers.Main).launch(coroutineExceptionHandler) {
            withContext(Dispatchers.IO) {
                Thread.sleep(2000)
                movies = moviesModel.getMovies()
            }

            updateLayoutManagers()
            updateMovies(movies)

            homeSwipeRefreshLayout?.isRefreshing = false
        }
    }

    private fun updateData() {
        updateCategoriesData()
        updateMoviesData()
    }

    private fun updateCategories(categories: List<CategoryDto>) {
        categoriesAdapter.categories = categories
        categoriesAdapter.notifyDataSetChanged()
    }

    private fun updateMovies(movies: List<MovieDto>) {
        moviesAdapter.movies = movies
        moviesAdapter.notifyDataSetChanged()
    }

    private fun getGridColumnsCount(itemWidth: Int): Int {
        return (resources.displayMetrics.widthPixels / itemWidth)
    }

    private fun showToast(message: String?) {
        when {
            message.isNullOrEmpty() -> {
                showToast(getString(R.string.main_empty_message))
            }
            else -> Toast.makeText(view?.context, message, Toast.LENGTH_SHORT).show()
        }
    }
}

const val MOVIE_DTO_BUNDLE_KEY = "movieDto"