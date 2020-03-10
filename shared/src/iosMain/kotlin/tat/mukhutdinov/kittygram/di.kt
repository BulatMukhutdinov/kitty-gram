package tat.mukhutdinov.kittygram

import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import tat.mukhutdinov.kittygram.infrastructure.di.DiModules
import tat.mukhutdinov.kittygram.kittyList.domain.KittyListIosInteractor

private val kodein = Kodein.lazy {
    import(DiModules.modules)

    // TODO remove custom interactor as soon as KN would support suspended functions
    bind<KittyListIosInteractor>() with provider {
        KittyListIosInteractor(instance())
    }
}

// https://github.com/Kodein-Framework/Kodein-DI/issues/290
fun getKittyListDomain() = kodein.direct.instance<KittyListIosInteractor>()