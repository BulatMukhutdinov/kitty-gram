package tat.mukhutdinov.kittygram.kittyList.gateway.dto

import kotlinx.serialization.Serializable

@Serializable
data class KittyResponse(
    val thumb: String?,
    val icon: String?,
    val breed: String?,
    val about: String?,
    val description: String?
)