package com.davidsantiagoiriarte.presentation.screens.guiascreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidsantiagoiriarte.domain.model.Guia
import com.davidsantiagoiriarte.domain.usecases.SuspendUseCase
import com.davidsantiagoiriarte.presentation.model.ViewGuia
import com.davidsantiagoiriarte.presentation.model.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuiaViewModel @Inject constructor(private val buscarGuiaUseCase: SuspendUseCase<String, Guia>) :
    ViewModel() {

    private var _guia = MutableStateFlow<ViewGuia?>(null)
    val guia: StateFlow<ViewGuia?> = _guia.asStateFlow()

    var noInternetFlag = MutableStateFlow(false)

    fun cargarGuia(numeroGuia: String) {
        viewModelScope.launch {
            try {
                val guia = buscarGuiaUseCase.execute(numeroGuia).map()
                _guia.value = guia
                noInternetFlag.value = false
            } catch (ex: Exception) {
                noInternetFlag.value = true
            }
        }
    }
}
