package com.mtsteta.homework.view.recyclerviews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mtsteta.homework.R
import com.mtsteta.homework.model.dto.MovieDto
import com.mtsteta.homework.view.recyclerviews.viewholders.EmptyMoviesListViewHolder
import com.mtsteta.homework.view.recyclerviews.viewholders.MoviesViewHolder

class MoviesRecyclerAdapter(private val callbackFunction: (movieDto: MovieDto) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: MutableList<MovieDto> = ArrayList()

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
        val lp = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        lp.isFullSpan = position == itemCount - 1 && (position + 1) % 2 == 1
        holder.itemView.layoutParams = lp

        when (holder) {
            is MoviesViewHolder -> {
                holder.bind(data[position])
            }
            is EmptyMoviesListViewHolder -> {
                holder.bind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (data.isEmpty()) {
            true -> TYPE_EMPTY
            else -> TYPE_MOVIE
        }
    }

    override fun getItemCount(): Int {
        return when (data.isEmpty()) {
            true -> 1
            else -> data.size
        }
    }

    fun initData(movies: List<MovieDto>?) {
        if (movies != null) {
            data.clear()
            data.addAll(movies)
            notifyDataSetChanged()
        }
    }
}

private const val TYPE_EMPTY = 0
private const val TYPE_MOVIE = 1