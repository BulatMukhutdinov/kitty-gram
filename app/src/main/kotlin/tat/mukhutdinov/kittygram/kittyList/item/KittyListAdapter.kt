package tat.mukhutdinov.kittygram.kittyList.item

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tat.mukhutdinov.kittygram.kittyList.domain.model.Kitty

class KittyListAdapter(
    private val bindings: KittyListItemBindings
) : RecyclerView.Adapter<KittyListViewHolder>() {

    val items = mutableListOf<Kitty>()

    fun submitList(updated: List<Kitty>) {
        val diffUtil = KittyDiffUtil(items, updated)

        val diffResult = DiffUtil.calculateDiff(diffUtil)
        diffResult.dispatchUpdatesTo(this)

        items.clear()
        items.addAll(updated)
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: KittyListViewHolder, position: Int) =
        holder.bindTo(items[position], bindings)

    override fun onViewRecycled(holder: KittyListViewHolder) {
        super.onViewRecycled(holder)

        holder.onViewRecycled()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KittyListViewHolder =
        KittyListViewHolder.create(parent)

    private class KittyDiffUtil(
        private val oldList: List<Kitty>,
        private val newList: List<Kitty>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].breed == newList[newItemPosition].breed

        override fun getOldListSize(): Int =
            oldList.size

        override fun getNewListSize(): Int =
            newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]
    }
}