package com.mtsteta.homework.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtsteta.homework.R
import com.mtsteta.homework.model.dto.CategoryDto
import com.mtsteta.homework.view.recyclerviews.adapters.CategoriesRecyclerAdapter
import com.mtsteta.homework.view.recyclerviews.decorations.RightSpaceItemDecoration
import com.mtsteta.homework.view.recyclerviews.diffutils.callbacks.CategoriesCallback

class ProfileFragment : Fragment() {
    private var preferencesRecyclerView: RecyclerView? = null
    private lateinit var preferencesAdapter: CategoriesRecyclerAdapter
    private var preferences = listOf<CategoryDto>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData(view)
    }

    private fun loadData(view: View) {
        initDataSource()
        setupViews(view)
        setupDecorations()
        setupLayoutManagers()
        setupAdapters()
        updateData(listOf())
    }

    private fun initDataSource() {
        preferences = listOf(CategoryDto("боевики"), CategoryDto("драмы"), CategoryDto("комедии"))
    }

    private fun setupViews(view: View?) {
        preferencesRecyclerView = view?.findViewById(R.id.rvProfileInterests)
    }

    private fun setupDecorations() {
        val rightSpace = resources.getDimension(R.dimen.item_movie_category_end_margin).toInt()
        val rightSpaceItemDecoration = RightSpaceItemDecoration(rightSpace)
        preferencesRecyclerView?.addItemDecoration(rightSpaceItemDecoration)
    }

    private fun setupLayoutManagers() {
        preferencesRecyclerView?.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupAdapters() {
        val emptyCallback: (String) -> Unit = {}
        preferencesAdapter = CategoriesRecyclerAdapter(emptyCallback)
        preferencesRecyclerView?.adapter = preferencesAdapter
    }

    private fun updateData(newPreferences: List<CategoryDto>) {
        val preferencesCallback = CategoriesCallback(preferences, newPreferences)
        val preferencesDiff = DiffUtil.calculateDiff(preferencesCallback)
        //preferences = newPreferences
        //preferencesAdapter.data = preferences
        //preferencesDiff.dispatchUpdatesTo(preferencesAdapter)
        //preferencesAdapter.data = preferences
    }
}