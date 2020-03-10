package tat.mukhutdinov.kittygram

import android.app.Application
import com.github.aakira.napier.DebugAntilog
import com.github.aakira.napier.Napier
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import tat.mukhutdinov.kittygram.infrastructure.application
import tat.mukhutdinov.kittygram.infrastructure.di.DiModules
import kotlin.tat.mukhutdinov.kittygram.BuildConfig

open class App : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(DiModules.modules)
    }

    override fun onCreate() {
        super.onCreate()

        application = this

        if (BuildConfig.DEBUG) {
            Napier.base(DebugAntilog())
        }
    }
}