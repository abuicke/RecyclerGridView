package ie.moses.testrecyclergridview

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import ie.moses.recyclergridview.GridRecyclerViewAdapter
import ie.moses.recyclergridview.OnItemClickListener

class FriendsListAdapter(context: Context, data: List<Pair<String, String>>, listener: OnItemClickListener?) :
        GridRecyclerViewAdapter<FriendsListAdapter.ViewHolder, Pair<String, String>>(context, data, listener) {

    override fun onCreateRowViewHolder(parent: ViewGroup, rowType: Int): ViewHolder {
        @LayoutRes val gridRowId = R.layout.grid_row
        val layout = inflater.inflate(gridRowId, parent, false) as ViewGroup
        return ViewHolder(layout)
    }

    override fun onBindRowItem(rowHolder: ViewHolder, row: Int, column: Int, index: Int) {
        val friendView = rowHolder.views[column]
        friendView.visibility = View.VISIBLE
        friendView.setOnClickListener { onItemClickListener?.onItemClick(index) }

        val facebookFriend = data[index]
        friendView.setName(facebookFriend.first)
        friendView.setProfilePic(facebookFriend.second)
    }

    override fun onBindEmptyRowItem(rowHolder: ViewHolder, row: Int, column: Int, index: Int) {
        rowHolder.views[column].visibility = View.INVISIBLE
    }

    override fun getRowType(position: Int) = position % 2

    inner class ViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {

        internal val views = ArrayList<FriendView>(rowSize)

        init {
            for (i in 0 until rowSize) {
                @LayoutRes val gridItemId = R.layout.grid_item
                val gridItem = inflater.inflate(gridItemId, itemView, false)
                views.add(gridItem.findViewById(R.id.friendView))
                itemView.addView(gridItem)
            }
        }
    }

}