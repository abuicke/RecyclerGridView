package ie.moses.recyclergridview.test

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import ie.moses.recyclergridview.R
import ie.moses.recyclergridview.core.GridRecyclerViewAdapter
import ie.moses.recyclergridview.core.OnItemClickListener

class FriendsListAdapter(context: Context, data: List<Pair<String, String>>, listener: OnItemClickListener?) :
        GridRecyclerViewAdapter<FriendsListAdapter.ViewHolder, Pair<String, String>>(context, data, listener) {

    companion object {
        private val TAG = FriendsListAdapter::class.simpleName
    }

    override fun onCreateRowViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        @LayoutRes val gridRowId = R.layout.grid_row
        val layout = inflater.inflate(gridRowId, parent, false) as ViewGroup
        return ViewHolder(layout)
    }

    /**
     * TODO: Add resetBoundView type method for cases such as the visibility like this. Should it be part of the top most parent class?
     * */
    override fun onBindRowItem(rowHolder: ViewHolder, row: Int, column: Int, index: Int) {
        val friendView = rowHolder.views[column]
        friendView.visibility = View.VISIBLE
        friendView.setOnClickListener({ onItemClickListener?.onItemClick(index) })

        val facebookFriend = data[index]
        friendView.setName(facebookFriend.first)
        friendView.setProfilePic(facebookFriend.second)
    }

    override fun onBindEmptyRowItem(rowHolder: ViewHolder, row: Int, column: Int, index: Int) {
        rowHolder.views[column].visibility = View.INVISIBLE
    }

    inner class ViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {

        internal val views = ArrayList<FriendView>(rowSize)

        init {
            Log.i("mo", "row size = $rowSize")
            for (i in 0 until rowSize) {
                @LayoutRes val gridItemId = R.layout.grid_item
                val gridItem = inflater.inflate(gridItemId, itemView, false)
                views.add(gridItem.findViewById(R.id.friendView))
                itemView.addView(gridItem)
            }
        }
    }

}