package com.davidsantiagoiriarte.data.network

import com.davidsantiagoiriarte.data.localstorage.entities.*

fun APIGuia.map(identificacionCliente: String) = DBGuia(
    identificacionCliente,
    destinatario.map(),
    estado_guia,
    fecha_envio,
    guia,
    total_unidades,
    ubicacion_guia?.getOrNull(0),
    ubicacion_guia?.getOrNull(1),
    novedad,
    fecha_novedad
)

fun APIDestinatario.map() = DBDestinatario(
    nombre,
    telefono,
    tipo_poblacion_destino,
    zonificacion.map()
)

fun APIZonificacion.map() = DBZonificacion(
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

fun APIUnidad.map() = DBUnidad(
    etiqueta1d,
    etiqueta2d,
    guia,
    numero_unidad,
    referencia_detalle
)
