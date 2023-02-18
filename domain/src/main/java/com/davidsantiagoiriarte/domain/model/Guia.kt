package com.davidsantiagoiriarte.domain.model

data class Guia(
    val destinatario: Destinatario,
    val estado_guia: String,
    val fecha_envio: String,
    val guia: String,
    val total_unidades: Int,
    val ubicacion_guia: List<Double>?,
    val unidades: List<Unidad>,
    val novedad: String? = null,
    val fecha_novedad: String? = null
)
