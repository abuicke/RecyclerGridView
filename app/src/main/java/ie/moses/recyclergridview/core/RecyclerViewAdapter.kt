package ie.moses.recyclergridview.core

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

interface OnItemClickListener {

    fun onItemClick(index: Int)
}

/**
 * TODO: Access `data` through protected var now
 * TODO: Needs to be a second library added to this one's build.gradle.
 * */
abstract class RecyclerViewAdapter<ViewHolder : RecyclerView.ViewHolder, T : Any>(
        context: Context, protected var data: List<T>, protected var onItemClickListener: OnItemClickListener?) :
        RecyclerView.Adapter<ViewHolder>() {

    protected var inflater: LayoutInflater = LayoutInflater.from(context)
}
