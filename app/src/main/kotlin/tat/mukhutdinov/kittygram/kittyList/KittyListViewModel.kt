package tat.mukhutdinov.kittygram.kittyList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import tat.mukhutdinov.kittygram.infrastructure.BaseViewModel
import tat.mukhutdinov.kittygram.kittyList.KittyListViewModelDirections.Companion.toKittyProfile
import tat.mukhutdinov.kittygram.kittyList.domain.boundary.KittyListDomain
import tat.mukhutdinov.kittygram.kittyList.domain.model.Kitty
import tat.mukhutdinov.kittygram.kittyList.item.KittyListAdapter
import tat.mukhutdinov.kittygram.kittyList.item.KittyListItemBindings
import timber.log.Timber
import kotlin.tat.mukhutdinov.kittygram.R
import kotlin.tat.mukhutdinov.kittygram.databinding.KittyListBinding

class KittyListViewModel : BaseViewModel<KittyListBinding>(), KittyListBindings {

    override val layoutId: Int = R.layout.kitty_list

    private val domain: KittyListDomain by instance()

    private val list = MutableLiveData<List<Kitty>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val backward = MaterialSharedAxis.create(requireContext(), MaterialSharedAxis.Z, true)
        enterTransition = backward

        val forward = MaterialSharedAxis.create(requireContext(), MaterialSharedAxis.Z, false)
        exitTransition = forward

        fragmentScope.launch {
            list.postValue(domain.getAll())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = KittyListAdapter(object : KittyListItemBindings {

            override fun onItemClicked(view: View, item: Kitty) {
                val extras: FragmentNavigator.Extras = FragmentNavigatorExtras(view to item.breed)

                navigate(toKittyProfile(item), extras)
            }
        })

        viewBinding.kitties.adapter = adapter
        viewBinding.kitties.setHasFixedSize(true)

        list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewBinding.refresh.setOnRefreshListener {
            viewScope.launch {
                try {
                    val refreshed = domain.refresh()
                    list.postValue(refreshed)
                } catch (throwable: Throwable) {
                    Timber.e(throwable)
                } finally {
                    viewBinding.refresh.isRefreshing = false
                }
            }
        }
    }
}