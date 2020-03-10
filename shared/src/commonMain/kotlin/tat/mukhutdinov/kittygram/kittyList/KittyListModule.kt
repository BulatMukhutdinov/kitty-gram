package tat.mukhutdinov.kittygram.kittyList

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import tat.mukhutdinov.kittygram.kittyList.domain.KittyListInteractor
import tat.mukhutdinov.kittygram.kittyList.domain.boundary.KittyListDomain
import tat.mukhutdinov.kittygram.kittyList.domain.boundary.KittyListGateway
import tat.mukhutdinov.kittygram.kittyList.gateway.KittyListLocalGateway

object KittyListModule {

    val module = Kodein.Module("CommonKittyListModule") {

        bind<KittyListGateway>() with provider {
            KittyListLocalGateway(instance())
        }

        bind<KittyListDomain>() with provider {
            KittyListInteractor(instance())
        }
    }
}