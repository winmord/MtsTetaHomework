package com.mtsteta.homework.presentation.recyclerviews.decorations

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RightSpaceItemDecoration(private val mSpaceWidth: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.right = (mSpaceWidth * Resources.getSystem().displayMetrics.density).toInt()
    }
}