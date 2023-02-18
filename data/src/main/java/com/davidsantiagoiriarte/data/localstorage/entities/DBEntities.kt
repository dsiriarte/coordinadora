package com.davidsantiagoiriarte.data.localstorage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBCliente(
    @PrimaryKey val identificacion: String,
    val guias: List<DBGuia>
)

@Entity
data class DBGuia(
    val destinatario: DBDestinatario,
    val estado_guia: String,
    val fecha_envio: String,
    val guia: String,
    val total_unidades: Int,
    val ubicacion_guia: List<Double>? = null,
    val unidades: List<DBUnidad>,
    val novedad: String? = null,
    val fecha_novedad: String? = null
)

@Entity
data class DBDestinatario(
    val nombre: String,
    val telefono: String,
    val tipo_poblacion_destino: String,
    val zonificacion: DBZonificacion
)

@Entity
data class DBZonificacion(
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

@Entity
data class DBUnidad(
    val etiqueta1d: String,
    val etiqueta2d: String,
    val guia: String,
    val numero_unidad: Int,
    val referencia_detalle: String
)
