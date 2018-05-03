package ie.moses.recyclerviewadapter.core

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

abstract class RecyclerViewAdapter<ViewHolder : RecyclerView.ViewHolder, T : Any>(
        context: Context, protected var data: List<T>, protected var onItemClickListener: OnItemClickListener?)
    : RecyclerView.Adapter<ViewHolder>() {

    protected var inflater: LayoutInflater = LayoutInflater.from(context)
}
