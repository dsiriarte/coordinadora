package com.davidsantiagoiriarte.domain.repository

import com.davidsantiagoiriarte.domain.model.Guia
import kotlinx.coroutines.flow.Flow

interface GuiasRepository {

    suspend fun sincronizarGuias()
    suspend fun buscarGuiasCliente(identificacionCliente: String): Flow<List<Guia>>
    suspend fun buscarGuia(numeroGuia: String): Guia
}
