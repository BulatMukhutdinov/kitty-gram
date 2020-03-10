package tat.mukhutdinov.kittygram.infrastructure.kittyProfile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import tat.mukhutdinov.kittygram.infrastructure.BaseViewModel
import kotlin.tat.mukhutdinov.kittygram.R
import kotlin.tat.mukhutdinov.kittygram.databinding.KittyProfileBinding

class KittyProfileViewModel : BaseViewModel<KittyProfileBinding>(), KittyProfileBindings {

    override val layoutId: Int = R.layout.kitty_profile

    private val args: KittyProfileViewModelArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.kitty = args.kitty
    }
}