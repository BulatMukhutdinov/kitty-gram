package tat.mukhutdinov.kittygram.kittyList.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import tat.mukhutdinov.kittygram.infrastructure.util.Glide
import tat.mukhutdinov.kittygram.kittyList.domain.model.Kitty
import kotlin.tat.mukhutdinov.kittygram.R
import kotlin.tat.mukhutdinov.kittygram.databinding.KittyListItemBinding

class KittyListViewHolder(
    private val viewBinding: KittyListItemBinding
) : RecyclerView.ViewHolder(viewBinding.root), LifecycleOwner {

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    fun bindTo(item: Kitty, bindings: KittyListItemBindings) {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)

        viewBinding.item = item
        viewBinding.bindings = bindings
        viewBinding.executePendingBindings()
    }

    fun onViewRecycled() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)

        viewBinding.unbind()

        Glide.withSafe(viewBinding.root)
            ?.clear(viewBinding.icon)
    }

    companion object {

        fun create(parent: ViewGroup): KittyListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val viewDataBinding: KittyListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.kitty_list_item, parent, false)

            return KittyListViewHolder(viewDataBinding)
        }
    }
}