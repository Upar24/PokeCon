package com.upar24.pokecon.data.remote.responses

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
)