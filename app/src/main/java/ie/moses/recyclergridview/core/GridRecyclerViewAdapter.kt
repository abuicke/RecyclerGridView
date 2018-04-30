package ie.moses.recyclergridview.core

import android.content.Context
import android.support.annotation.IntRange
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ie.moses.recyclergridview.util.roundUp

abstract class GridRecyclerViewAdapter<ViewHolder : RecyclerView.ViewHolder, T : Any>(
        context: Context, recyclerView: RecyclerView, data: List<T>, listener: OnItemClickListener? = null) :
        RecyclerViewAdapter<ViewHolder, T>(context, recyclerView, data, listener) {

    companion object {
        private val TAG = GridRecyclerViewAdapter::class.simpleName
    }

    var rowSize: Int = 3
        set(value) {
            if(value < 1) throw IllegalArgumentException("row size must be at least 1")
            field = value
            notifyDataSetChanged()
        }

    /**
     * TODO: What to do when the client wants to make is the the viewType, e.g. maybe every second
     * row should be different, e.g. one profile pic every other row then the normal row size for the others.
     * */
    abstract fun onCreateRowViewHolder(parent: ViewGroup, viewType: Int): ViewHolder

    abstract fun onBindRowItem(rowHolder: ViewHolder, row: Int, column: Int, index: Int)

    abstract fun onBindEmptyRowItem(rowHolder: ViewHolder, row: Int, column: Int, index: Int)

    final override fun getItemViewType(position: Int): Int = rowSize

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return onCreateRowViewHolder(parent, viewType)
    }

    final override fun onBindViewHolder(holder: ViewHolder, row: Int) {
        for (column in 0 until rowSize) {
            val index = (row * rowSize) + column
            if (data.size > index) {
                onBindRowItem(holder, row, column, index)
            } else {
                onBindEmptyRowItem(holder, row, column, index)
            }
        }
    }

    /**
     * TODO: Test to see of both these have to be doubles.
     * */
    final override fun getItemCount(): Int = (data.size.toDouble() / rowSize.toDouble()).roundUp()

}