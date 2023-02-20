package com.davidsantiagoiriarte.data.di

import com.davidsantiagoiriarte.data.localstorage.daos.GuiasDao
import com.davidsantiagoiriarte.data.localstorage.daos.UnidadesDao
import com.davidsantiagoiriarte.data.network.ClientesService
import com.davidsantiagoiriarte.data.repository.GuiasRepositoryImpl
import com.davidsantiagoiriarte.domain.repository.GuiasRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGuiasRepository(
        clientesService: ClientesService,
        guiasDao: GuiasDao,
        unidadesDao: UnidadesDao
    ): GuiasRepository {
        return GuiasRepositoryImpl(clientesService, guiasDao, unidadesDao)
    }

}
