package com.mtsteta.homework.presentation.recyclerviews.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtsteta.homework.R
import com.mtsteta.homework.data.dto.CategoryDto

class CategoriesViewHolder(itemView: View, onCategoryItemClick: (title: String) -> Unit) : RecyclerView.ViewHolder(itemView) {
    private val movieCategoryTextView: TextView = itemView.findViewById(R.id.textItemMovieCategory)

    lateinit var item: String

    init {
        movieCategoryTextView.setOnClickListener { onCategoryItemClick.invoke(item) }
    }

    fun bind(categoryDto: CategoryDto) {
        item = categoryDto.name
        movieCategoryTextView.text = categoryDto.name
    }
}