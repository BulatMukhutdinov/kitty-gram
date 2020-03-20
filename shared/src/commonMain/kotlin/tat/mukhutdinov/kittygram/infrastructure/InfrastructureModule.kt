package tat.mukhutdinov.kittygram.infrastructure

import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.provider

expect fun provideSqlDriver(): SqlDriver

object InfrastructureModule {

    val module = Kodein.Module("CommonInfrastructureModule") {

        bind<SqlDriver>() with provider {
            provideSqlDriver()
        }

        bind<HttpClient>() with provider {
            HttpClient {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(Json.nonstrict)
                }
            }
        }
    }
}