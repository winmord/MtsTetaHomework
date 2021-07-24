package com.mtsteta.homework.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtsteta.homework.R
import com.mtsteta.homework.data.dto.CategoryDto
import com.mtsteta.homework.presentation.recyclerviews.adapters.CategoriesRecyclerAdapter
import com.mtsteta.homework.presentation.recyclerviews.decorations.RightSpaceItemDecoration

class ProfileFragment : Fragment() {
    private lateinit var preferencesRecyclerView: RecyclerView
    private lateinit var preferencesAdapter: CategoriesRecyclerAdapter
    private var preferences = listOf<CategoryDto>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        preferences = listOf(CategoryDto("боевики"), CategoryDto("драмы"), CategoryDto("комедии"))
        preferencesRecyclerView = view.findViewById(R.id.rvProfileInterests)
        val rightSpace = resources.getDimension(R.dimen.item_movie_category_end_margin).toInt()
        val rightSpaceItemDecoration = RightSpaceItemDecoration(rightSpace)
        preferencesRecyclerView.addItemDecoration(rightSpaceItemDecoration)
        preferencesRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        val emptyCallback: (String) -> Unit = {}
        preferencesAdapter = CategoriesRecyclerAdapter(emptyCallback)
        preferencesRecyclerView.adapter = preferencesAdapter
        preferencesAdapter.categories = preferences

        return view
    }
}