package tat.mukhutdinov.kittygram.kittyList.domain.boundary

import tat.mukhutdinov.kittygram.kittyList.domain.model.Kitty

interface KittyListDomain {

    suspend fun getAll(): List<Kitty>
}