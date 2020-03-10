package tat.mukhutdinov.kittygram.infrastructure

import com.squareup.sqldelight.db.SqlDriver
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.provider

expect fun provideSqlDriver(): SqlDriver

object InfrastructureModule {

    val module = Kodein.Module("CommonInfrastructureModule") {

        bind<SqlDriver>() with provider {
            provideSqlDriver()
        }
    }
}