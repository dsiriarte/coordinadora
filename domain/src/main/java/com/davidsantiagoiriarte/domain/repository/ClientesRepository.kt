package com.davidsantiagoiriarte.domain.repository

import com.davidsantiagoiriarte.domain.model.Cliente

interface ClientesRepository {

    suspend fun sincronizarClientes()

    suspend fun buscarCliente(identificacion: String): Cliente

}
