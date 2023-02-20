package com.davidsantiagoiriarte.domain.usecases

import com.davidsantiagoiriarte.domain.exceptions.IdentificacionIncorrectaException
import com.davidsantiagoiriarte.domain.model.Guia
import com.davidsantiagoiriarte.domain.repository.GuiasRepository
import kotlinx.coroutines.flow.Flow

class BuscarGuiasClienteUseCase(
    private val guiasRepository: GuiasRepository
) : UseCase<String, Flow<List<Guia>>> {

    override suspend fun execute(identificacion: String): Flow<List<Guia>> {
        if (identificacion.length < 8 || identificacion.length > 15)
            throw IdentificacionIncorrectaException()

        return guiasRepository.buscarGuiasCliente(identificacion)
    }
}
