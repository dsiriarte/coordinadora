package com.davidsantiagoiriarte.domain.repository

import com.davidsantiagoiriarte.domain.model.Guia
import kotlinx.coroutines.flow.Flow

interface GuiasRepository {

    suspend fun sincronizarGuias()

    suspend fun buscarGuia(identificacionCliente: String): Flow<List<Guia>>

}
