package com.davidsantiagoiriarte.presentation.screens.guiascreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidsantiagoiriarte.domain.model.Guia
import com.davidsantiagoiriarte.domain.usecases.UseCase
import com.davidsantiagoiriarte.presentation.model.ViewGuia
import com.davidsantiagoiriarte.presentation.model.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuiaViewModel @Inject constructor(private val buscarGuiaUseCase: UseCase<String, Guia>) :
    ViewModel() {

    private var _guia = MutableStateFlow<ViewGuia?>(null)
    val guia: StateFlow<ViewGuia?> = _guia.asStateFlow()

    fun cargarGuia(numeroGuia: String) {
        viewModelScope.launch {
            val guia = buscarGuiaUseCase.execute(numeroGuia).map()
            _guia.value = guia
        }
    }
}
