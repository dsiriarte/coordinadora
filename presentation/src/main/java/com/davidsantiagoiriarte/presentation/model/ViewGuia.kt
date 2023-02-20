package com.davidsantiagoiriarte.presentation.model

import androidx.compose.ui.graphics.Color
import com.davidsantiagoiriarte.domain.model.Destinatario
import com.davidsantiagoiriarte.domain.model.Unidad

data class ViewGuia(
    val destinatario: Destinatario,
    val estadoGuia: String,
    val iconoGuia: Int,
    val colorBackground: Color,
    val fechaEnvio: String,
    val guia: String,
    val totalUnidades: Int,
    val ubicacionGuiaLat: Double? = null,
    val ubicacionGuiaLng: Double? = null,
    val unidades: List<Unidad>,
    val novedad: String? = null,
    val fechaNovedad: String? = null
)
