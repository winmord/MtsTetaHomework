package com.mtsteta.homework.presentation.recyclerviews.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtsteta.homework.R
import com.mtsteta.homework.data.dto.CategoryDto

class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val movieCategoryTextView: TextView =
        itemView.findViewById(R.id.textItemMovieCategory)

    fun bind(category: CategoryDto) {
        movieCategoryTextView.text = category.name
    }
}