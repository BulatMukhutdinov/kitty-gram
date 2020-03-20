package tat.mukhutdinov.kittygram.kittyList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import tat.mukhutdinov.kittygram.infrastructure.BaseViewModel
import tat.mukhutdinov.kittygram.kittyList.KittyListViewModelDirections.Companion.toTrackingProfiles
import tat.mukhutdinov.kittygram.kittyList.domain.boundary.KittyListDomain
import tat.mukhutdinov.kittygram.kittyList.domain.model.Kitty
import tat.mukhutdinov.kittygram.kittyList.item.KittyListAdapter
import tat.mukhutdinov.kittygram.kittyList.item.KittyListItemBindings
import kotlin.tat.mukhutdinov.kittygram.R
import kotlin.tat.mukhutdinov.kittygram.databinding.KittyListBinding

class KittyListViewModel : BaseViewModel<KittyListBinding>(), KittyListBindings {

    override val layoutId: Int = R.layout.kitty_list

    private val domain: KittyListDomain by instance()

    private val list = MutableLiveData<List<Kitty>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentScope.launch {
            list.postValue(domain.getAll())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = KittyListAdapter(object : KittyListItemBindings {

            override fun onItemClicked(item: Kitty) {
                navigate(toTrackingProfiles(item))
            }
        })

        viewBinding.kitties.adapter = adapter
        viewBinding.kitties.setHasFixedSize(true)

        list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewBinding.refresh.setOnRefreshListener {
            viewScope.launch {
                val refreshed = domain.refresh()

                viewBinding.refresh.isRefreshing = false

                list.postValue(refreshed)
            }
        }
    }
}