package tat.mukhutdinov.kittygram.kittyList.domain.model

import tat.mukhutdinov.kittygram.infrastructure.data.Parcelable
import tat.mukhutdinov.kittygram.infrastructure.data.Parcelize

@Parcelize
data class Kitty(
    val thumb: String,
    val icon: String,
    val breed: String,
    val about: String,
    val description: String
) : Parcelable