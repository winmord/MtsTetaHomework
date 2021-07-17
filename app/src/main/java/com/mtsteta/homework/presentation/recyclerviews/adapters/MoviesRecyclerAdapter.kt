package com.mtsteta.homework.presentation.recyclerviews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mtsteta.homework.R
import com.mtsteta.homework.data.dto.MovieDto
import com.mtsteta.homework.presentation.recyclerviews.viewholders.MoviesViewHolder

class MoviesRecyclerAdapter(private val callbackFunction: (title: String) -> Unit) :
    RecyclerView.Adapter<MoviesViewHolder>() {

    private lateinit var movies: List<MovieDto>

    fun setData(moviesList: List<MovieDto>) {
        movies = moviesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener { callbackFunction(movies[position].title) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}