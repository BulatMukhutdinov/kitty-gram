package tat.mukhutdinov.kittygram.infrastructure.di

import org.kodein.di.Kodein
import tat.mukhutdinov.kittygram.infrastructure.InfrastructureModule
import tat.mukhutdinov.kittygram.kittyList.KittyListModule

object DiModules {

    val modules = Kodein.Module("CommonDi") {
        import(InfrastructureModule.module)
        import(KittyListModule.module)
    }
}