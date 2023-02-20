package com.davidsantiagoiriarte.domain.usecases

import com.davidsantiagoiriarte.domain.repository.GuiasRepository

class SincronizarGuiasUseCase(
    private val guiasRepository: GuiasRepository
) : SuspendUseCase<Unit, Unit> {

    override suspend fun execute(p: Unit) {
        guiasRepository.sincronizarGuias()
    }

}
