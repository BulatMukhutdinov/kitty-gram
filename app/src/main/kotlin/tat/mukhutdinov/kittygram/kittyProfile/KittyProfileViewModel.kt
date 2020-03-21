package tat.mukhutdinov.kittygram.kittyProfile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialSharedAxis
import tat.mukhutdinov.kittygram.infrastructure.BaseViewModel
import kotlin.tat.mukhutdinov.kittygram.R
import kotlin.tat.mukhutdinov.kittygram.databinding.KittyProfileBinding

class KittyProfileViewModel : BaseViewModel<KittyProfileBinding>(), KittyProfileBindings {

    override val layoutId: Int = R.layout.kitty_profile

    private val args: KittyProfileViewModelArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val forward = MaterialSharedAxis.create(requireContext(), MaterialSharedAxis.Z, true)
        enterTransition = forward

        val backward = MaterialSharedAxis.create(requireContext(), MaterialSharedAxis.Z, false)
        exitTransition = backward
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.kitty = args.kitty
        viewBinding.root.transitionName = args.kitty.breed
    }
}