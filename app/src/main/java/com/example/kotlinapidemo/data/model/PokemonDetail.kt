package com.example.kotlinapidemo.data.model

import com.google.gson.annotations.SerializedName

data class PokemonDetail (
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Int,
    @SerializedName("height") val height: Int
)