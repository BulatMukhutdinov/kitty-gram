package tat.mukhutdinov.kittygram

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import tat.mukhutdinov.kittygram.infrastructure.application
import tat.mukhutdinov.kittygram.infrastructure.di.DiModules
import timber.log.Timber
import kotlin.tat.mukhutdinov.kittygram.BuildConfig

open class App : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(DiModules.modules)
    }

    override fun onCreate() {
        super.onCreate()

        application = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}