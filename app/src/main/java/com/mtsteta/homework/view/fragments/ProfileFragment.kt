package com.mtsteta.homework.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtsteta.homework.R
import com.mtsteta.homework.model.dto.CategoryDto
import com.mtsteta.homework.view.recyclerviews.adapters.CategoriesRecyclerAdapter
import com.mtsteta.homework.view.recyclerviews.decorations.RightSpaceItemDecoration
import com.mtsteta.homework.view.recyclerviews.diffutils.callbacks.CategoriesCallback
import com.mtsteta.homework.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {
    private val profileViewModel: ProfileViewModel by viewModels()
    private var preferencesRecyclerView: RecyclerView? = null
    private lateinit var preferencesAdapter: CategoriesRecyclerAdapter

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
        setupViews(view)
        setupDecorations()
        setupLayoutManagers()
        setupAdapters()
        setupObservers()
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

    private fun setupObservers() {
        profileViewModel.preferencesDataList.observe(
            viewLifecycleOwner,
            Observer(preferencesAdapter::initData)
        )
        profileViewModel.loadData()
    }
}