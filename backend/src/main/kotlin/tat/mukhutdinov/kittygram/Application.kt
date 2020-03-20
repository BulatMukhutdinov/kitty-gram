package tat.mukhutdinov.kittygram

import com.google.gson.Gson
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing

val gson = Gson()

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson()
    }

    routing {
        get("/all") {
            call.respond(getAll())
        }
    }
}

private fun getAll(): Array<Kitty> {
    val data = ClassLoader.getSystemResource("data.json").readText()

    return gson.fromJson(data, Array<Kitty>::class.java)
}

