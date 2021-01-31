package ng.sam.distancer

import android.graphics.drawable.Drawable
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun setRecyclerViewLinearDecorator(
    recyclerView: RecyclerView,
    layoutManager: LinearLayoutManager,
    drawable: Drawable?
) {
    val dividerItemDecoration =
        DividerItemDecoration(recyclerView.context, layoutManager.orientation)
    if (drawable != null) {
        dividerItemDecoration.setDrawable(drawable)
    }
    recyclerView.addItemDecoration(dividerItemDecoration)
}

fun RecyclerView.setRecyclerViewLinearDecorator(
    @NonNull layoutManager: LinearLayoutManager, drawableRes: Int
) {
    setRecyclerViewLinearDecorator(
        this, layoutManager,
        if (drawableRes == -1) null else ContextCompat.getDrawable(
            this.context,
            drawableRes
        )
    )
}