package ie.moses.recyclergridview

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

interface OnItemClickListener {

    fun onItemClick(index: Int)
}

class GridRecyclerViewAdapter(context: Context, data: List<Pair<String, String>>, listener: OnItemClickListener? = null) :
        RecyclerView.Adapter<GridRecyclerViewAdapter.ViewHolder>() {

    companion object {
        private val TAG = GridRecyclerViewAdapter::class.simpleName
    }

    private val _context = context
    private val _data = data
    private val _onItemClickListener = listener

    var rowSize: Int = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(_context)
        @LayoutRes val gridRowId = R.layout.grid_row
        val layout = inflater.inflate(gridRowId, parent, false) as ViewGroup
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, row: Int) {
        for (i in 0 until rowSize) {
            val index = (row * rowSize) + i
            val friendView = holder.views[i]
            friendView.setOnClickListener({ _onItemClickListener?.onItemClick(index) })

            if (_data.size > index) {
                val facebookFriend = _data[index]
                friendView.setName(facebookFriend.first)
                friendView.setProfilePic(facebookFriend.second)
            } else {
                holder.views[i].visibility = View.INVISIBLE
            }
        }
    }

    override fun getItemCount(): Int = (_data.size.toDouble() / rowSize.toDouble()).roundUp()

    inner class ViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {

        val views = ArrayList<FriendView>(rowSize)

        init {
            for (i in 0 until rowSize) {
                val inflater = LayoutInflater.from(_context)
                @LayoutRes val gridItemId = R.layout.grid_item
                val gridItem = inflater.inflate(gridItemId, itemView, false) as FriendView
                views.add(gridItem)
                itemView.addView(gridItem)
            }
        }
    }

}