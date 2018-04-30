package ie.moses.recyclergridview.core

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ie.moses.recyclergridview.util.roundUp

abstract class GridRecyclerViewAdapter<ViewHolder : RecyclerView.ViewHolder, T : Any>(
        context: Context, data: List<T>, listener: OnItemClickListener? = null) :
        RecyclerViewAdapter<ViewHolder, T>(context, data, listener) {

    companion object {
        private val TAG = GridRecyclerViewAdapter::class.simpleName
    }

    /**
     * TODO: Needs invalidation if row size changes.
     * */
    var rowSize: Int = 3

    abstract fun onCreateRowViewHolder(parent: ViewGroup, viewType: Int): ViewHolder

    abstract fun onBindRowItem(rowHolder: ViewHolder, row: Int, column: Int, index: Int)

    abstract fun onBindEmptyRowItem(rowHolder: ViewHolder, row: Int, column: Int, index: Int)

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return onCreateRowViewHolder(parent, viewType)
    }

    final override fun onBindViewHolder(holder: ViewHolder, row: Int) {
        for (column in 0 until rowSize) {
            val index = (row * rowSize) + column
            if (data.size > index) {
                onBindRowItem(holder, row, column, index)
            }else {
                onBindEmptyRowItem(holder, row, column, index)
            }
        }
    }

    /**
     * TODO: Test to see of both these have to be doubles.
     * */
    final override fun getItemCount(): Int = (data.size.toDouble() / rowSize.toDouble()).roundUp()

}