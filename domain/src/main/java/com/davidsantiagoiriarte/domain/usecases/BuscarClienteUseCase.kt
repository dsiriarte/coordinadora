package com.davidsantiagoiriarte.domain.usecases

import com.davidsantiagoiriarte.domain.model.Cliente
import com.davidsantiagoiriarte.domain.repository.ClientesRepository

class BuscarClienteUseCase(
    private val clientesRepository: ClientesRepository
) : UseCase<String, Cliente> {

    override suspend fun execute(identificacion: String): Cliente {
       return clientesRepository.buscarCliente(identificacion)
    }
}
