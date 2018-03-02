package com.personaltrainer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView

/**
 * Created by Honza on 19.02.2018.
 */

open abstract class ExtendedRecyclerViewAdapter<VH : RecyclerView.ViewHolder?, I>(context: Context) :
        RecyclerView.Adapter<VH>() {
    protected val context: Context = context
    protected val items: MutableList<I> = mutableListOf()
    var itemClickListener: OnItemClickListener? = null

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder?.itemView?.setOnClickListener { _ ->
            itemClickListener?.onItemClick(position)
        }
        onBindItem(holder, position)
    }

    fun addItem(item: I) {
        items.add(item)
        notifyItemInserted(items.size -1)
    }

    fun getItem(position: Int) : I? {
        return items.getOrNull(position)
    }

    abstract fun onBindItem(holder: VH, position: Int)

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}