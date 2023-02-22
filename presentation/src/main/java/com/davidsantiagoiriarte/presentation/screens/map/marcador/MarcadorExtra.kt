package com.davidsantiagoiriarte.presentation.screens.map.marcador

import com.google.android.gms.maps.model.LatLng

data class MarcadorExtra(
    val name: String,
    val lat: Double,
    val lng: Double,
    val address: String,
    val isHomeIcon: Boolean
) : java.io.Serializable

fun MarcadorExtra.map() = Marcador(
    name,
    LatLng(lat, lng),
    address,
    isHomeIcon
)
