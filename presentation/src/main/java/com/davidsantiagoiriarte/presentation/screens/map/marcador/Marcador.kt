package com.davidsantiagoiriarte.presentation.screens.map.marcador

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class Marcador(
    val name: String,
    val latLng: LatLng,
    val address: String,
    val isHomeIcon: Boolean
) : ClusterItem {
    override fun getPosition(): LatLng =
        latLng

    override fun getTitle(): String =
        name

    override fun getSnippet(): String =
        address
}
