package com.davidsantiagoiriarte.domain

import com.davidsantiagoiriarte.domain.usecases.BuscarGuiaUseCase
import com.davidsantiagoiriarte.domain.usecases.BuscarGuiasClienteUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class BuscarGuiaUseCaseTest {

    @Test
    fun buscar_guia_usecase_debe_retornar_guia_correctamente() {
        val buscarGuiaUseCase = BuscarGuiaUseCase(FakeGuiasRepository)
        try {
            runBlocking {
                val guia = buscarGuiaUseCase.execute("12345678")
                Assert.assertEquals(fakeGuias[0], guia)
            }
            assert(true)
        } catch (ex: Exception) {
            assert(false)
        }
    }
}