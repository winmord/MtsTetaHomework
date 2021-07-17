package com.mtsteta.homework.presentation.recyclerviews.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtsteta.homework.R

class EmptyMoviesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val emptyMoviesListTextView: TextView =
        itemView.findViewById(R.id.itemEmptyMoviesListTextView)

    fun bind() {
        emptyMoviesListTextView.text = itemView.context.getString(R.string.empty_movies_list)
    }
}