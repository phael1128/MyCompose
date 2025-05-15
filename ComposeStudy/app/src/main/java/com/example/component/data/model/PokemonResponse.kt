package com.example.component.data.model

data class Response(
    val count: Int,
    val previous: String?,
    val next: String?,
    val results: List<Result>
) {
    data class Result(
        val url: String,
        val name: String
    )
}

data class PokemonResponse(
    val species: Species,
    val sprites: Sprites
) {
    data class Species(var name: String)

    data class Sprites(var frontDefault: String)
}