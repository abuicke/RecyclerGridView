package ie.moses.recyclergridview.core

import android.content.Context
import android.support.annotation.IntRange
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ie.moses.recyclergridview.util.Cantor
import ie.moses.recyclergridview.util.roundUp

abstract class GridRecyclerViewAdapter<ViewHolder : RecyclerView.ViewHolder, T : Any>(
        context: Context, data: List<T>, listener: OnItemClickListener? = null) :
        RecyclerViewAdapter<ViewHolder, T>(context, data, listener) {

    companion object {
        private val TAG = GridRecyclerViewAdapter::class.simpleName
    }

    @get:IntRange(from = 1, to = Cantor.MAX_INTEGER.toLong())
    @setparam:IntRange(from = 1, to = Cantor.MAX_INTEGER.toLong())
    var rowSize: Int = 3
        set(value) {
            if (value < 1) throw IllegalArgumentException("row size must be at least 1")
            if (value > Cantor.MAX_INTEGER) throw IllegalArgumentException("row size cannot exceed ${Cantor.MAX_INTEGER}")
            field = value
            notifyDataSetChanged()
        }

    protected abstract fun onCreateRowViewHolder(parent: ViewGroup, rowType: Int): ViewHolder

    protected abstract fun onBindRowItem(rowHolder: ViewHolder, row: Int, column: Int, index: Int)

    protected abstract fun onBindEmptyRowItem(rowHolder: ViewHolder, row: Int, column: Int, index: Int)

    @IntRange(from = 0, to = Cantor.MAX_INTEGER.toLong())
    protected open fun getRowType(position: Int) = 0

    final override fun getItemViewType(position: Int): Int {
        val rowType = getRowType(position)
        if (rowType > Cantor.MAX_INTEGER.toLong()) {
            throw IllegalStateException("$TAG.getItemViewType(Int) must return a value " +
                    "between 0 and ${Cantor.MAX_INTEGER.toLong()}, instead returned $rowType")
        }

        return Cantor.pair(rowSize, rowType)
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return onCreateRowViewHolder(parent, Cantor.depair(viewType).second)
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

    final override fun getItemCount() = (data.size.toDouble() / rowSize).roundUp()

}