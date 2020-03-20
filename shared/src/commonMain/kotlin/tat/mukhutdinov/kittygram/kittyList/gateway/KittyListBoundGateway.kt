package tat.mukhutdinov.kittygram.kittyList.gateway

import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import tat.mukhutdinov.kittygram.Database
import tat.mukhutdinov.kittygram.kittyList.domain.boundary.KittyListGateway
import tat.mukhutdinov.kittygram.kittyList.domain.model.Kitty
import tat.mukhutdinov.kittygram.kittyList.gateway.dto.KittyResponse

class KittyListBoundGateway(driver: SqlDriver, private val httpClient: HttpClient) :
    KittyListGateway {

    private val db = Database(driver)

    private val queries = db.kittyGramQueries

    override suspend fun getAll(): List<Kitty> {
        return queries
            .selectAll { breed, description, icon, thumb, about ->
                Kitty(
                    icon = icon,
                    thumb = thumb,
                    breed = breed,
                    description = description,
                    about = about
                )
            }
            .executeAsList()
    }

    override suspend fun refresh() {
        val updated: List<KittyResponse> = httpClient.get("http://192.168.50.227:8080/all")

        queries.clear()

        updated
            .map {
                tat.mukhutdinov.kittygram.Kitty.Impl(
                    icon = it.icon.orEmpty(),
                    thumb = it.thumb.orEmpty(),
                    breed = it.breed.orEmpty(),
                    description = it.description.orEmpty(),
                    about = it.about.orEmpty()
                )
            }
            .forEach(queries::insert)
    }
}