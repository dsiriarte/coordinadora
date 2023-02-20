package com.davidsantiagoiriarte.presentation.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidsantiagoiriarte.domain.model.Guia
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
    private val sincronizarGuiasUseCase: UseCase<Unit, Unit>
) : ViewModel() {

    private var _guias = MutableStateFlow(listOf<ViewGuia>())
    val guias: StateFlow<List<ViewGuia>> = _guias.asStateFlow()

    init {
        viewModelScope.launch {
            sincronizarGuiasUseCase.execute(Unit)
        }
    }

    fun buscarGuiasCliente(identificacion: String) {
        viewModelScope.launch {
            buscarGuiasClienteUseCase.execute(identificacion).collectLatest { guias ->
                _guias.value = guias.map { it.map() }
            }
        }
    }

    fun ordenarListaPorFecha() {
        _guias.value = _guias.value.sortedBy { it.fechaEnvio }
    }
}
