package com.mtsteta.homework.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mtsteta.homework.R
import com.mtsteta.homework.data.dto.CategoryDto
import com.mtsteta.homework.data.dto.MovieDto
import com.mtsteta.homework.data.features.categories.CategoriesDataSourceImpl
import com.mtsteta.homework.data.features.movies.MoviesDataSourceImpl
import com.mtsteta.homework.presentation.fragments.HomeFragment
import com.mtsteta.homework.presentation.fragments.ProfileFragment
import com.mtsteta.homework.presentation.models.CategoriesModel
import com.mtsteta.homework.presentation.models.MoviesModel
import com.mtsteta.homework.presentation.recyclerviews.adapters.CategoriesRecyclerAdapter
import com.mtsteta.homework.presentation.recyclerviews.adapters.MoviesRecyclerAdapter
import com.mtsteta.homework.presentation.recyclerviews.decorations.BottomSpaceItemDecoration
import com.mtsteta.homework.presentation.recyclerviews.decorations.RightSpaceItemDecoration
import com.mtsteta.homework.presentation.recyclerviews.diffutils.callbacks.CategoriesCallback
import com.mtsteta.homework.presentation.recyclerviews.diffutils.callbacks.MoviesCallback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.nav_view)

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_navigation, HomeFragment())
                        .commit()
                }
                R.id.navigation_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_navigation, ProfileFragment())
                        .commit()
                }
            }
            supportFragmentManager.popBackStack()
            true
        }
    }
}