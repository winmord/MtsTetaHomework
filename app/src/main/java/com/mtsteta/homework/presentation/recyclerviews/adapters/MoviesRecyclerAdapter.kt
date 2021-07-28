package com.mtsteta.homework.presentation.recyclerviews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mtsteta.homework.R
import com.mtsteta.homework.data.dto.MovieDto
import com.mtsteta.homework.presentation.recyclerviews.viewholders.EmptyMoviesListViewHolder
import com.mtsteta.homework.presentation.recyclerviews.viewholders.MoviesViewHolder

class MoviesRecyclerAdapter(private val callbackFunction: (movieDto: MovieDto) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var movies: List<MovieDto>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_EMPTY -> EmptyMoviesListViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_empty_movies_list, parent, false)
            )
            TYPE_MOVIE -> MoviesViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movie, parent, false), callbackFunction
            )
            else -> throw IllegalStateException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MoviesViewHolder -> {
                holder.bind(movies[position])
            }
            is EmptyMoviesListViewHolder -> {
                holder.bind()
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (movies.isEmpty()) {
            true -> TYPE_EMPTY
            else -> TYPE_MOVIE
        }
    }

    override fun getItemCount(): Int {
        return when (movies.isEmpty()) {
            true -> 1
            else -> movies.size
        }
    }
}

private const val TYPE_EMPTY = 0
private const val TYPE_MOVIE = 1