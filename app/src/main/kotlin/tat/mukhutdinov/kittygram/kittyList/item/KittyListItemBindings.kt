package tat.mukhutdinov.kittygram.kittyList.item

import tat.mukhutdinov.kittygram.kittyList.domain.model.Kitty

interface KittyListItemBindings {

    fun onItemClicked(item: Kitty)
}