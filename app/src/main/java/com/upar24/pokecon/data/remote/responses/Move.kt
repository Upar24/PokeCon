package com.upar24.pokecon.data.remote.responses

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)