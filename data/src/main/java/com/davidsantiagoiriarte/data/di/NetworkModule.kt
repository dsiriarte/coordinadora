package com.davidsantiagoiriarte.data.di

import com.davidsantiagoiriarte.data.network.ClientesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideClientesService(): ClientesService {
        return provideRetrofit().create(ClientesService::class.java)
    }
}

const val BASE_URL = "https://us-central1-cm-challenges-api.cloudfunctions.net/"
