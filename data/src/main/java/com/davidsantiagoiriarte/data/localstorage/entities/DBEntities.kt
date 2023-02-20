package com.davidsantiagoiriarte.data.localstorage.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

data class DBGuiaConUnidades(
    @Embedded val guia : DBGuia,
    val ubicacion_guia_lng: Double? = null,
    @Relation(
        parentColumn = "guia",
        entityColumn = "guia"
    )
    val unidades: List<DBUnidad>
)

@Entity
data class DBGuia(
    val identificacion_cliente: String,
    @Embedded val destinatario: DBDestinatario,
    val estado_guia: String,
    val fecha_envio: String,
    @PrimaryKey val guia: String,
    val total_unidades: Int,
    val ubicacion_lat: Double? = null,
    val ubicacion_lng: Double? = null,
    val novedad: String? = null,
    val fecha_novedad: String? = null
)


data class DBDestinatario(
    val nombre: String,
    val telefono: String,
    val tipo_poblacion_destino: String,
    @Embedded val zonificacion: DBZonificacion
)

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
    @PrimaryKey val numero_unidad: Int,
    val referencia_detalle: String
)
