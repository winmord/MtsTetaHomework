package com.mtsteta.homework.view.recyclerviews.diffutils.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.mtsteta.homework.model.dto.MovieDto

class MoviesCallback(private val oldList: List<MovieDto>, private val newList: List<MovieDto>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].title == newList[newItemPosition].title

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}