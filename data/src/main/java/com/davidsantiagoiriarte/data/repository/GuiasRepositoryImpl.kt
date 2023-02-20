package com.davidsantiagoiriarte.data.repository

import com.davidsantiagoiriarte.data.localstorage.daos.GuiasDao
import com.davidsantiagoiriarte.data.localstorage.daos.UnidadesDao
import com.davidsantiagoiriarte.data.localstorage.map
import com.davidsantiagoiriarte.data.network.ClientesService
import com.davidsantiagoiriarte.data.network.map
import com.davidsantiagoiriarte.domain.model.Guia
import com.davidsantiagoiriarte.domain.repository.GuiasRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GuiasRepositoryImpl(
    private val clientesService: ClientesService,
    private val guiasDao: GuiasDao,
    private val unidadesDao: UnidadesDao
) : GuiasRepository {
    override suspend fun sincronizarGuias() {
        val clienteNetwork = clientesService.getClientes()
        clienteNetwork.clientes.forEach { (identificacion, apiCliente) ->
            apiCliente.guias.forEach { apiGuia ->
                guiasDao.insert(apiGuia.map(identificacion))
                unidadesDao.insertAll(apiGuia.unidades.map { it.map() })
            }
        }
    }

    override suspend fun buscarGuia(identificacionCliente: String): Flow<List<Guia>> {
        return guiasDao.buscarGuiasCliente(identificacionCliente)
            .map { dbGuia -> dbGuia.map { it.map() } }
    }

}
