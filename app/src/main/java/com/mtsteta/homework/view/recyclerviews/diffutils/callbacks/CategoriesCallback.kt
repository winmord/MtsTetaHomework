package com.mtsteta.homework.view.recyclerviews.diffutils.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.mtsteta.homework.model.dto.CategoryDto

class CategoriesCallback(private val oldList: List<CategoryDto>,
                         private val newList: List<CategoryDto>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].name == newList[newItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}