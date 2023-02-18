package com.davidsantiagoiriarte.data.localstorage

import com.davidsantiagoiriarte.data.localstorage.entities.*
import com.davidsantiagoiriarte.domain.model.*

fun DBCliente.map() = Cliente(
    identificacion,
    guias.map { it.map() }
)

fun DBGuia.map() = Guia(
    destinatario.map(),
    estado_guia,
    fecha_envio,
    guia,
    total_unidades,
    ubicacion_guia,
    unidades.map { it.map() },
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
