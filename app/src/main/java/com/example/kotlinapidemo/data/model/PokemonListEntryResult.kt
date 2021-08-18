package com.example.kotlinapidemo.data.model

import com.google.gson.annotations.SerializedName

data class PokemonListEntryResult(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)