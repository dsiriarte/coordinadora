package com.davidsantiagoiriarte.data.repository

import com.davidsantiagoiriarte.data.localstorage.daos.ClientesDao
import com.davidsantiagoiriarte.data.localstorage.map
import com.davidsantiagoiriarte.data.network.ClientesService
import com.davidsantiagoiriarte.data.network.map
import com.davidsantiagoiriarte.domain.model.Cliente
import com.davidsantiagoiriarte.domain.repository.ClientesRepository

class ClientesRepositoryImpl(
    private val clientesService: ClientesService,
    private val clientesDao: ClientesDao
) : ClientesRepository {

    override suspend fun sincronizarClientes() {
        val clientesNetwork = clientesService.getClientes()
        clientesDao.insertAll(clientesNetwork.map())
    }

    override suspend fun buscarCliente(identificacion: String): Cliente {
        return clientesDao.buscarCliente(identificacion).map()
    }
}
