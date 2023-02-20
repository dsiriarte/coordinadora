package com.davidsantiagoiriarte.domain.usecases

import com.davidsantiagoiriarte.domain.model.Guia
import com.davidsantiagoiriarte.domain.repository.GuiasRepository

class BuscarGuiaUseCase(private val guiasRepository: GuiasRepository) : UseCase<String, Guia> {
    override suspend fun execute(numeroGuia: String): Guia {
        return guiasRepository.buscarGuia(numeroGuia)
    }
}
