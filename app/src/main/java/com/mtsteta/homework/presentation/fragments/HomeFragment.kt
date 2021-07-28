package com.mtsteta.homework.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    private lateinit var moviesModel: MoviesModel
    private lateinit var categoriesModel: CategoriesModel
    private var categoriesRecyclerView: RecyclerView? = null
    private var moviesRecyclerView: RecyclerView? = null
    private var homeSwipeRefreshLayout: SwipeRefreshLayout? = null
    private lateinit var categoriesAdapter: CategoriesRecyclerAdapter
    private lateinit var moviesAdapter: MoviesRecyclerAdapter
    private var categories = listOf<CategoryDto>()
    private var movies = listOf<MovieDto>()
    private val activityCallbackFunction: (String) -> Unit = { showToast(it) }

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
        setupAdapters()
        updateData()
    }

    private fun initDataSource() {
        moviesModel = MoviesModel(MoviesDataSourceImpl())
        categoriesModel = CategoriesModel(CategoriesDataSourceImpl())
    }

    private fun setupViews(view: View?) {
        categoriesRecyclerView = view?.findViewById(R.id.rvCategories)
        moviesRecyclerView = view?.findViewById(R.id.rvMovies)
        homeSwipeRefreshLayout = view?.findViewById(R.id.srlHomeFragment)
    }

    private fun setupListeners() {
        homeSwipeRefreshLayout?.setOnRefreshListener {
            updateData()
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
        categoriesRecyclerView?.layoutManager =
            LinearLayoutManager(view?.context, LinearLayoutManager.HORIZONTAL, false)

        val itemWidth = resources.getDimension(R.dimen.item_movie_width).toInt()
        val itemMargin = resources.getDimension(R.dimen.item_movie_end_margin).toInt()
        val moviesRecyclerViewItemWidth = itemWidth + itemMargin
        val moviesRecyclerViewColumnsCount: Int = getGridColumnsCount(moviesRecyclerViewItemWidth)
        moviesRecyclerView?.layoutManager =
            GridLayoutManager(view?.context, moviesRecyclerViewColumnsCount)
    }

    private fun setupAdapters() {
        categoriesAdapter = CategoriesRecyclerAdapter(activityCallbackFunction)
        categoriesAdapter.categories = categories
        categoriesRecyclerView?.adapter = categoriesAdapter

        val moviesCallbackFunction: (MovieDto) -> Unit = {
            val bundle = bundleOf(MOVIE_DTO_BUNDLE_KEY to it)
            view?.findNavController()?.navigate(R.id.movieDetailsFragment, bundle)
        }

        moviesAdapter = MoviesRecyclerAdapter(moviesCallbackFunction)
        moviesAdapter.movies = movies
        moviesRecyclerView?.adapter = moviesAdapter
    }

    private fun updateData() {
        CoroutineScope(Dispatchers.Main).launch {
            categoriesAdapter.categories = withContext(Dispatchers.IO) {
                categoriesModel.getCategories()
            }
            moviesAdapter.movies = withContext(Dispatchers.IO) {
                moviesModel.getMovies()
            }

            homeSwipeRefreshLayout?.isRefreshing = false

            categoriesAdapter.notifyDataSetChanged()
            moviesAdapter.notifyDataSetChanged()
        }
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