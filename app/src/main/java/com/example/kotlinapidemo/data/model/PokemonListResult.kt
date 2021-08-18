package com.example.kotlinapidemo.data.model

import com.google.gson.annotations.SerializedName

data class PokemonListResult(
    @SerializedName("count") val count: String,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val results: List<PokemonListEntryResult>
)