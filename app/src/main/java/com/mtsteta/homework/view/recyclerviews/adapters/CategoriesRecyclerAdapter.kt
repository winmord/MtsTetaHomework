package com.mtsteta.homework.view.recyclerviews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mtsteta.homework.R
import com.mtsteta.homework.model.dto.CategoryDto
import com.mtsteta.homework.view.recyclerviews.viewholders.CategoriesViewHolder

class CategoriesRecyclerAdapter(private val callbackFunction: (title: String) -> Unit) :
    RecyclerView.Adapter<CategoriesViewHolder>() {
    var data: MutableList<CategoryDto> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_category, parent, false)
        return CategoriesViewHolder(itemView, callbackFunction)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun initData(categories: List<CategoryDto>?) {
        if (categories != null) {
            data.clear()
            data.addAll(categories)
            notifyDataSetChanged()
        }
    }
}