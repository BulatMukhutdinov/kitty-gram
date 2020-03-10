package tat.mukhutdinov.kittygram.kittyList.domain

import tat.mukhutdinov.kittygram.kittyList.domain.boundary.KittyListDomain
import tat.mukhutdinov.kittygram.kittyList.domain.boundary.KittyListGateway
import tat.mukhutdinov.kittygram.kittyList.domain.model.Kitty

class KittyListInteractor(private val gateway: KittyListGateway) : KittyListDomain {

    override suspend fun getAll(): List<Kitty> =
        gateway.getAll()
            .sortedBy(Kitty::breed)
}