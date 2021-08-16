package com.mtsteta.homework.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mtsteta.homework.R
import com.mtsteta.homework.model.dto.MovieDto
import com.mtsteta.homework.view.recyclerviews.adapters.CategoriesRecyclerAdapter
import com.mtsteta.homework.view.recyclerviews.adapters.MoviesRecyclerAdapter
import com.mtsteta.homework.view.recyclerviews.decorations.BottomSpaceItemDecoration
import com.mtsteta.homework.view.recyclerviews.decorations.RightSpaceItemDecoration
import com.mtsteta.homework.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()

    private var categoriesRecyclerView: RecyclerView? = null
    private var moviesRecyclerView: RecyclerView? = null

    private var homeSwipeRefreshLayout: SwipeRefreshLayout? = null

    private lateinit var categoriesAdapter: CategoriesRecyclerAdapter
    private lateinit var moviesAdapter: MoviesRecyclerAdapter

    private val activityCallbackFunction: (String) -> Unit = { showToast(it) }
    private val moviesCallbackFunction: (MovieDto) -> Unit = {
        val bundle = bundleOf(MOVIE_DTO_BUNDLE_KEY to it)
        view?.findNavController()?.navigate(R.id.movieDetailsFragment, bundle)
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
        setupViews(view)
        setupListeners()
        setupDecorations()
        setupLayoutManagers()
        setupAdapters()
        setupObservers()
    }

    private fun setupViews(view: View?) {
        categoriesRecyclerView = view?.findViewById(R.id.rvCategories)
        moviesRecyclerView = view?.findViewById(R.id.rvMovies)
        homeSwipeRefreshLayout = view?.findViewById(R.id.srlMoviesList)
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
        val moviesSpanCount = getGridColumnsCount(moviesRecyclerViewItemWidth)

        moviesRecyclerView?.layoutManager =
            StaggeredGridLayoutManager(moviesSpanCount, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun setupAdapters() {
        categoriesAdapter = CategoriesRecyclerAdapter(activityCallbackFunction)
        categoriesRecyclerView?.adapter = categoriesAdapter

        moviesAdapter = MoviesRecyclerAdapter(moviesCallbackFunction)
        moviesRecyclerView?.adapter = moviesAdapter
    }

    private fun setupObservers() {
        homeViewModel.categoriesDataList.observe(
            viewLifecycleOwner,
            Observer(categoriesAdapter::initData)
        )
        homeViewModel.moviesDataList.observe(viewLifecycleOwner, Observer(moviesAdapter::initData))
        homeViewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
        homeViewModel.loadData()
    }

    private fun updateData() {
        homeViewModel.loadData()
    }

    data class ViewState(
        val isRefreshing: Boolean = true
    )

    private fun render(viewState: ViewState) = with(viewState) {
        homeSwipeRefreshLayout?.isRefreshing = isRefreshing
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