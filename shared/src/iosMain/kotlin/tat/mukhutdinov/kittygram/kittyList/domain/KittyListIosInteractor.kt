package tat.mukhutdinov.kittygram.kittyList.domain

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tat.mukhutdinov.kittygram.infrastructure.coroutines.MainDispatcher
import tat.mukhutdinov.kittygram.kittyList.domain.boundary.KittyListDomain
import tat.mukhutdinov.kittygram.kittyList.domain.model.Kitty

class KittyListIosInteractor(private val kittyListDomain: KittyListDomain) {

    fun getAll(result: (List<Kitty>) -> Unit) {
        GlobalScope.launch(MainDispatcher) { result(kittyListDomain.getAll()) }
    }

    fun refresh(result: (List<Kitty>) -> Unit) {
        GlobalScope.launch(MainDispatcher) { result(kittyListDomain.refresh()) }
    }
}