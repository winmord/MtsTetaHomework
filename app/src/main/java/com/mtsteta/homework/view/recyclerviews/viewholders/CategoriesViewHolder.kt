package com.mtsteta.homework.view.recyclerviews.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtsteta.homework.R
import com.mtsteta.homework.model.dto.CategoryDto

class CategoriesViewHolder(itemView: View, onCategoryItemClick: (title: String) -> Unit) : RecyclerView.ViewHolder(itemView) {
    private val movieCategoryTextView: TextView = itemView.findViewById(R.id.textItemMovieCategory)

    lateinit var item: CategoryDto

    init {
        movieCategoryTextView.setOnClickListener { onCategoryItemClick.invoke(item.name) }
    }

    fun bind(categoryDto: CategoryDto) {
        item = categoryDto
        movieCategoryTextView.text = categoryDto.name
    }
}