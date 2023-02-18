package com.davidsantiagoiriarte.data.network

import com.davidsantiagoiriarte.data.localstorage.entities.*

fun APIClientes.map(): List<DBCliente> {
    return clientes.map {
        DBCliente(
            it.key,
            it.value.guias.map { guia -> guia.map() }
        )
    }
}

fun APIGuia.map() = DBGuia(
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
