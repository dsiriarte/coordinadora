package com.davidsantiagoiriarte.domain

import com.davidsantiagoiriarte.domain.model.Guia
import com.davidsantiagoiriarte.domain.repository.GuiasRepository
import com.davidsantiagoiriarte.domain.usecases.BuscarGuiasClienteUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class BuscarGuiasClienteUseCaseTest {

    @Test
    fun buscar_guiaas_cliente_usecase_no_debe_aceptar_identificacion_menor_a_8_digitos() {
        val buscarGuiasClienteUseCase = BuscarGuiasClienteUseCase(FakeGuiasRepository)
        try {
            buscarGuiasClienteUseCase.execute("1234")
            assert(false)
        } catch (ex: Exception) {
            assert(true)
        }
    }

    @Test
    fun buscar_guiaas_cliente_usecase_no_debe_aceptar_identificacion_mayor_a_15_digitos (){
        val buscarGuiasClienteUseCase = BuscarGuiasClienteUseCase(FakeGuiasRepository)
        try {
            buscarGuiasClienteUseCase.execute("1234567891234567")
            assert(false)
        } catch (ex: Exception) {
            assert(true)
        }

    }

    @Test
    fun buscar_guiaas_cliente_usecase_debe_retornar_guias_correctamente(){
        val buscarGuiasClienteUseCase = BuscarGuiasClienteUseCase(FakeGuiasRepository)
        try {
            runBlocking {
                val guias = buscarGuiasClienteUseCase.execute("12345678").first()
                assertEquals(fakeGuias, guias)
            }
            assert(true)
        } catch (ex: Exception) {
            assert(false)
        }
    }

}
