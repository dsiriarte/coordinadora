package com.davidsantiagoiriarte.domain.model

data class Destinatario(
    val nombre: String,
    val telefono: String,
    val tipo_poblacion_destino: String,
    val zonificacion: Zonificacion
)
