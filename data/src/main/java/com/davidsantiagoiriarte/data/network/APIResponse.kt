package com.davidsantiagoiriarte.data.network

data class APIClientes(
    val clientes: Map<String, APICliente>
)

data class APICliente(
    val guias: List<APIGuia>
)

data class APIGuia(
    val destinatario: APIDestinatario,
    val estado_guia: String,
    val fecha_envio: String,
    val guia: String,
    val total_unidades: Int,
    val ubicacion_guia: List<Double>,
    val unidades: List<APIUnidad>,
    val novedad: String? = null,
    val fecha_novedad: String? = null
)

data class APIDestinatario(
    val nombre: String,
    val telefono: String,
    val tipo_poblacion_destino: String,
    val zonificacion: APIZonificacion
)

data class APIZonificacion(
    val ciudad: String,
    val codigo_terminal: String,
    val dane: String,
    val direccion: String,
    val equipo: String,
    val lat: Double,
    val lng: Double,
    val zona_hub: String,
    val zona_postal: String
)

data class APIUnidad(
    val etiqueta1d: String,
    val etiqueta2d: String,
    val guia: String,
    val numero_unidad: Int,
    val referencia_detalle: String
)
