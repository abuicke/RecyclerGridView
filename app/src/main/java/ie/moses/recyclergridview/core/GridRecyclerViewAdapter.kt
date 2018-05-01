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

    /**
     * TODO: What to do when the client wants to make is the the viewType, e.g. maybe every second
     * row should be different, e.g. one profile pic every other row then the normal row size for the others.
     * */
    protected abstract fun onCreateRowViewHolder(parent: ViewGroup, rowType: Int): ViewHolder

    protected abstract fun onBindRowItem(rowHolder: ViewHolder, row: Int, column: Int, index: Int)

    protected abstract fun onBindEmptyRowItem(rowHolder: ViewHolder, row: Int, column: Int, index: Int)

    protected open fun getRowItemType(position: Int) = 0

    /**
     * TODO: Number type conversions are bullshit.
     * */
    final override fun getItemViewType(position: Int): Int =
            Cantor.pair(rowSize.toLong(), getRowItemType(position).toLong()).toInt()

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        /**
         * TODO: All these number type conversaions are retarded and can I override first and second
         * fields in Pair?
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

    /**
     * TODO: Test to see of both these have to be doubles.
     * */
    final override fun getItemCount(): Int = (data.size.toDouble() / rowSize.toDouble()).roundUp()

}