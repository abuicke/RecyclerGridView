package ie.moses.recyclergridview.core

import android.content.Context
import android.support.annotation.IntRange
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ie.moses.recyclergridview.util.Cantor
import ie.moses.recyclergridview.util.roundUp

abstract class GridRecyclerViewAdapter<ViewHolder : RecyclerView.ViewHolder, T : Any>(
        context: Context, recyclerView: RecyclerView, data: List<T>, listener: OnItemClickListener? = null) :
        RecyclerViewAdapter<ViewHolder, T>(context, recyclerView, data, listener) {

    companion object {
        private val TAG = GridRecyclerViewAdapter::class.simpleName
    }

    @get:IntRange(from = 1)
    var rowSize: Int = 3
        set(value) {
            if (value < 1) throw IllegalArgumentException("row size must be at least 1")
            field = value
            notifyDataSetChanged()
        }

    protected abstract fun onCreateRowViewHolder(parent: ViewGroup, rowType: Int): ViewHolder

    protected abstract fun onBindRowItem(rowHolder: ViewHolder, row: Int, column: Int, index: Int)

    protected abstract fun onBindEmptyRowItem(rowHolder: ViewHolder, row: Int, column: Int, index: Int)

    protected open fun getRowItemType(position: Int) = 0

    /**
     * TODO: There are situations where through no fault of the clients -1 is returned. What to do about long to int conversion?
     * */
    final override fun getItemViewType(position: Int): Int {
        val cantorValue = Cantor.pair(rowSize.toLong(), getRowItemType(position).toLong())
        return if (cantorValue != cantorValue.toInt().toLong()) {
            cantorValue.toInt()
        } else {
            -1
        }
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        /**
         * TODO: Cantor depairing is messy, needs to be cleaned up, probably shouldn't be using
         * Pair as Pair.second is ambiguous.
         * */
        return onCreateRowViewHolder(parent, Cantor.depair(viewType.toLong()).second.toInt())
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