package com.davidsantiagoiriarte.domain.usecases

import com.davidsantiagoiriarte.domain.repository.ClientesRepository

class SincronizarClientesUseCase(
    private val clientesRepository: ClientesRepository
) : UseCase<Unit, Unit> {

    override suspend fun execute(p: Unit) {
        clientesRepository.sincronizarClientes()
    }

}
