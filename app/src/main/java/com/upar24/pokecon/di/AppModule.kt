package com.upar24.pokecon.di

import com.upar24.pokecon.data.remote.PokeConApi
import com.upar24.pokecon.repository.PokeConRepository
import com.upar24.pokecon.util.Constans.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providePokeConRepository(
        api:PokeConApi
    )= PokeConRepository(api)

    fun providePokeApi(): PokeConApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PokeConApi::class.java)
    }

}














