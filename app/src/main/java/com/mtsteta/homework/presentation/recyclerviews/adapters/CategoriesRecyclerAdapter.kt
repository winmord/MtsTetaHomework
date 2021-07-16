package com.mtsteta.homework.presentation.recyclerviews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtsteta.homework.R
import com.mtsteta.homework.data.dto.CategoryDto

class CategoriesRecyclerAdapter(
    private val categories: List<CategoryDto>,
    private val listener: (title: String) -> Unit
) :
    RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_category, parent, false)
        return CategoriesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.movieCategoryTextView.text = categories[position].category
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var movieCategoryTextView: TextView

        init {
            itemView.setOnClickListener(this)
            movieCategoryTextView = itemView.findViewById(R.id.itemMovieCategoryTextView)
        }

        override fun onClick(v: View?) {
            listener(categories[adapterPosition].category)
        }
    }
}