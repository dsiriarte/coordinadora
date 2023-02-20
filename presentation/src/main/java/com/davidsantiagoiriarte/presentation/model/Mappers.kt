package com.davidsantiagoiriarte.presentation.model

import com.davidsantiagoiriarte.domain.model.Guia
import com.davidsantiagoiriarte.presentation.R
import com.davidsantiagoiriarte.presentation.ui.theme.enNySColor
import com.davidsantiagoiriarte.presentation.ui.theme.enRepartoColor
import com.davidsantiagoiriarte.presentation.ui.theme.enTerminalColor
import com.davidsantiagoiriarte.presentation.ui.theme.entregadaColor

fun Guia.map() = ViewGuia(
    destinatario,
    estado_guia,
    when (estado_guia) {
        "En Reparto" -> R.drawable.reparto
        "En NyS" -> R.drawable.nys
        "Entregada" -> R.drawable.entregada
        else -> R.drawable.terminal
    },
    when (estado_guia) {
        "En Reparto" -> enRepartoColor
        "En NyS" -> enNySColor
        "Entregada" -> entregadaColor
        else -> enTerminalColor
    },
    fecha_envio,
    guia,
    total_unidades,
    ubicacion_guia_lat,
    ubicacion_guia_lng,
    unidades,
    novedad,
    fecha_novedad
)
