package tat.mukhutdinov.kittygram.kittyList.gateway

import com.squareup.sqldelight.db.SqlDriver
import tat.mukhutdinov.kittygram.Database
import tat.mukhutdinov.kittygram.kittyList.domain.boundary.KittyListGateway
import tat.mukhutdinov.kittygram.kittyList.domain.model.Kitty

class KittyListLocalGateway(driver: SqlDriver) : KittyListGateway {

    private val db = Database(driver)

    private val queries = db.kittyGramQueries

    override suspend fun getAll(): List<Kitty> {
        return queries
            .selectAll { breed, description, icon ->
                Kitty(
                    icon = icon,
                    breed = breed,
                    description = description
                )
            }
            .executeAsList()
    }
}