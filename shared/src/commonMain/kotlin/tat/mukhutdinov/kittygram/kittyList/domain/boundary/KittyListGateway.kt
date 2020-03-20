package tat.mukhutdinov.kittygram.kittyList.domain.boundary

import tat.mukhutdinov.kittygram.kittyList.domain.model.Kitty

interface KittyListGateway {

    suspend fun getAll(): List<Kitty>

    suspend fun refresh()
}