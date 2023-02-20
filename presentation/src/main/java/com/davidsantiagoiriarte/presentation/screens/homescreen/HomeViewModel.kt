package com.davidsantiagoiriarte.presentation.screens.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidsantiagoiriarte.domain.model.Guia
import com.davidsantiagoiriarte.domain.usecases.SuspendUseCase
import com.davidsantiagoiriarte.domain.usecases.UseCase
import com.davidsantiagoiriarte.presentation.model.ViewGuia
import com.davidsantiagoiriarte.presentation.model.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val buscarGuiasClienteUseCase: UseCase<String, Flow<List<Guia>>>,
    private val sincronizarGuiasUseCase: SuspendUseCase<Unit, Unit>
) : ViewModel() {

    private var _guias = MutableStateFlow(listOf<ViewGuia>())
    private var _guiasFiltradas = MutableStateFlow(listOf<ViewGuia>())
    val guias: StateFlow<List<ViewGuia>> = _guiasFiltradas.asStateFlow()


    var mostrarError = MutableStateFlow(false)
    var mensajeError = MutableStateFlow("")

    var noInternetFlag = MutableStateFlow(false)

    init {
        sincronizar()
    }

    private fun sincronizar() {
        viewModelScope.launch {
            try {
                sincronizarGuiasUseCase.execute(Unit)
                noInternetFlag.value = false
            } catch (ex: Exception) {
                noInternetFlag.value = true
            }
        }
    }

    fun buscarGuiasCliente(identificacion: String) {
        sincronizar()
        viewModelScope.launch {
            try {
                buscarGuiasClienteUseCase.execute(identificacion).collectLatest { guias ->
                    _guias.value = guias.map { it.map() }
                    _guiasFiltradas.value = guias.map { it.map() }
                }
            } catch (ex: Exception) {
                mostrarError.value = true
                ex.message?.let { mensajeError.value = it }
            }
        }
    }

    fun ordenarListaPorFecha() {
        _guiasFiltradas.value = _guias.value.sortedBy { it.fechaEnvio }
    }

    fun filtrarPorEstado(estado: String?) {
        if (estado.isNullOrEmpty()) {
            _guiasFiltradas.value = _guias.value
        } else {
            _guiasFiltradas.value = _guias.value.filter { it.estadoGuia == estado }
        }
    }
}
