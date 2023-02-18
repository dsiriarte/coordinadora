package com.davidsantiagoiriarte.domain.model

data class Zonificacion(
    val ciudad: String,
    val codigo_terminal: String,
    val dane: String,
    val direccion: String,
    val equipo: String,
    val lat: Double?,
    val lng: Double?,
    val zona_hub: String,
    val zona_postal: String
)
