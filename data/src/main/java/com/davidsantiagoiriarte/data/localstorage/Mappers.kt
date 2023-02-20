package com.davidsantiagoiriarte.data.localstorage

import com.davidsantiagoiriarte.data.localstorage.entities.*
import com.davidsantiagoiriarte.domain.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun DBGuiaConUnidades.map() = Guia(
    guia.destinatario.map(),
    guia.estado_guia,
    guia.fecha_envio,
    guia.guia,
    guia.total_unidades,
    guia.ubicacion_lat,
    guia.ubicacion_lng,
    unidades.map { it.map() },
    guia.novedad,
    guia.fecha_novedad
)

fun DBGuia.map(unidades: List<Unidad>) = Guia(
    destinatario.map(),
    estado_guia,
    fecha_envio,
    guia,
    total_unidades,
    ubicacion_lat,
    ubicacion_lng,
    unidades,
    novedad,
    fecha_novedad
)

fun DBDestinatario.map() = Destinatario(
    nombre,
    telefono,
    tipo_poblacion_destino,
    zonificacion.map()
)

fun DBZonificacion.map() = Zonificacion(
    ciudad,
    codigo_terminal,
    dane,
    direccion,
    equipo,
    lat,
    lng,
    zona_hub,
    zona_postal
)

fun DBUnidad.map() = Unidad(
    etiqueta1d,
    etiqueta2d,
    guia,
    numero_unidad,
    referencia_detalle
)
