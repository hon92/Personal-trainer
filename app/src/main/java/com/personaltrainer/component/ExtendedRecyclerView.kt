package com.personaltrainer.component

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View

/**
 * Created by Honza on 17.02.2018.
 */
open class ExtendedRecyclerView : RecyclerView {
    var emptyView: View? = null
    set(emptyView) {
        field = emptyView
        handleAdapterSize()
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) :
            super(context, attributeSet, defStyle)

    private val adapterDataObserver: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            handleAdapterSize()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            handleAdapterSize()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            handleAdapterSize()
        }
    }

    private fun handleAdapterSize() {
        val isEmpty = (adapter?.itemCount ?: 0) == 0
        if (isEmpty) {
            visibility = View.INVISIBLE
            emptyView?.visibility = View.VISIBLE
        } else {
            visibility = View.VISIBLE
            emptyView?.visibility = View.INVISIBLE
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(adapterDataObserver)
        adapter?.registerAdapterDataObserver(adapterDataObserver)
        super.setAdapter(adapter)
    }
}