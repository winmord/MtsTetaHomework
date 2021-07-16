package com.mtsteta.homework.presentation.recyclerviews.decorations

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BottomSpaceItemDecoration(private val mSpaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = (mSpaceHeight * Resources.getSystem().displayMetrics.density).toInt()
    }
}