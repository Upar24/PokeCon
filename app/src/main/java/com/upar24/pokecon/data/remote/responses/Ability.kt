package com.upar24.pokecon.data.remote.responses

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)