package com.upar24.pokecon.repository

import com.upar24.pokecon.data.remote.PokeConApi
import com.upar24.pokecon.data.remote.responses.Pokemon
import com.upar24.pokecon.data.remote.responses.PokemonList
import com.upar24.pokecon.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokeConRepository @Inject constructor(
    private val api: PokeConApi
) {
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>{
        val response = try {
            api.getPokemonList(limit,offset)
        }catch (e: Exception){
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon>{
        val response = try {
            api.getPokemonInfo(pokemonName)
        }catch (e: Exception){
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }
}