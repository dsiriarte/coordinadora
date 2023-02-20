package com.davidsantiagoiriarte.coordinadora.di

import com.davidsantiagoiriarte.domain.model.Guia
import com.davidsantiagoiriarte.domain.repository.GuiasRepository
import com.davidsantiagoiriarte.domain.usecases.BuscarGuiasClienteUseCase
import com.davidsantiagoiriarte.domain.usecases.SincronizarGuiasUseCase
import com.davidsantiagoiriarte.domain.usecases.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideSincronizarGuiasUseCase(guiasRepository: GuiasRepository): UseCase<Unit, Unit> {
        return SincronizarGuiasUseCase(guiasRepository)
    }

    @Provides
    @Singleton
    fun provideBuscarGuiasClienteUseCase(guiasRepository: GuiasRepository): UseCase<String, Flow<List<Guia>>> {
        return BuscarGuiasClienteUseCase(guiasRepository)
    }
}
