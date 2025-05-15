package com.example.component.data.service

import com.example.component.data.model.PokemonResponse
import com.example.component.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon/")
    suspend fun getPokemons(): Response

    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response

    @GET("pokemon/{pid}/")
    suspend fun getPokemon(@Path("pid") pid: Int): PokemonResponse
}