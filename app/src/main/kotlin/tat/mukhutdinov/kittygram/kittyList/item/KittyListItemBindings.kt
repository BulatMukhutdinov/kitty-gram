package tat.mukhutdinov.kittygram.kittyList.item

import android.view.View
import tat.mukhutdinov.kittygram.kittyList.domain.model.Kitty

interface KittyListItemBindings {

    fun onItemClicked(view: View, item: Kitty)
}