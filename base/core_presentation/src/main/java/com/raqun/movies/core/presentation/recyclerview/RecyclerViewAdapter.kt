package com.raqun.movies.core.presentation.recyclerview

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Single

class RecyclerViewAdapter constructor(
    private val items: MutableList<DisplayItem> = ArrayList(),
    private val itemComperator: DisplayItemComperator,
    private val viewHolderFactoryMap: Map<Int, ViewHolderFactory>,
    private val viewBinderFactoryMap: Map<Int, ViewHolderBinder>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), DiffAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        viewHolderFactoryMap[viewType]?.createViewHolder(parent)!!

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        viewBinderFactoryMap[items[position].type()]?.bind(holder, items[position])
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
        Single.fromCallable { calculateDiffResult(items) }
            .doOnSuccess { updateAllItems(items) }
            .subscribe(this::updateWithOnlyDiffResult)
    }

    override fun updateItems(newItems: List<DisplayItem>) {
        items.run {
            clear()
            addAll(newItems)
        }
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

    private fun calculateDiffResult(newItems: List<DisplayItem>): DiffUtil.DiffResult =
        calculateDiff(newItems)

}