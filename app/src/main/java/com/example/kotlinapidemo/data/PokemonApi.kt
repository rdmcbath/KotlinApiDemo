package com.example.kotlinapidemo.data

import com.example.kotlinapidemo.data.model.PokemonDetail
import com.example.kotlinapidemo.data.model.PokemonListResult
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon")
    fun getPokemonList() :  Single<Result<PokemonListResult>>

    @GET("pokemon/{name}")
    fun getDetailByName(@Path("name") name : String) : Single<Result<PokemonDetail>>

}