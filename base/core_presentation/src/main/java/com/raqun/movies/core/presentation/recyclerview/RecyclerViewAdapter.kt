package com.raqun.movies.core.presentation.recyclerview

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RecyclerViewAdapter constructor(
    private val items: MutableList<DisplayItem> = ArrayList(),
    private val itemComperator: DisplayItemComperator,
    private val viewHolderFactoryMap: Map<Int, ViewHolderFactory>,
    private val viewBinderFactoryMap: Map<Int, ViewHolderBinder>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), DiffAdapter {

    var itemClickListener: ((item: DisplayItem) -> Unit)? = null
    var itemLongClickListener: ((item: DisplayItem) -> Boolean)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        viewHolderFactoryMap[viewType]?.createViewHolder(parent)!!

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        viewBinderFactoryMap[items[position].type()]?.bind(holder, items[position])
        (holder as ViewHolder<*>).itemClickListener = itemClickListener
        holder.itemLongClickListener = itemLongClickListener
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].type()

    override fun update(newItems: List<DisplayItem>) {
        if (items.isEmpty()) {
            updateAllItems(newItems)
        } else {
            updateOnlyChangedItems(newItems)
        }
    }

    override fun updateAllItems(newItems: List<DisplayItem>) {
        updateItems(newItems)
        notifyDataSetChanged()
    }

    @SuppressLint("CheckResult")
    override fun updateOnlyChangedItems(newItems: List<DisplayItem>) {
        Single.fromCallable { calculateDiffResult(newItems) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { updateItems(newItems) }
            .subscribe(this::updateWithOnlyDiffResult)
    }

    override fun updateItems(newItems: List<DisplayItem>) {
        items.clear()
        items.addAll(newItems)
    }

    override fun calculateDiff(newItems: List<DisplayItem>): DiffUtil.DiffResult =
        DiffUtil.calculateDiff(
            DiffUtilImpl(
                items,
                newItems,
                itemComperator
            )
        )

    override fun updateWithOnlyDiffResult(result: DiffUtil.DiffResult) {
        result.dispatchUpdatesTo(this)
    }

    override fun addItems(newItems: List<DisplayItem>) {
        val startRange = items.size
        items.addAll(newItems)
        notifyItemRangeChanged(startRange, newItems.size)
    }

    private fun calculateDiffResult(newItems: List<DisplayItem>): DiffUtil.DiffResult =
        calculateDiff(newItems)
}