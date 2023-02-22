package com.davidsantiagoiriarte.presentation.screens.map.marcador

import android.content.Context
import androidx.core.content.ContextCompat
import com.davidsantiagoiriarte.presentation.R
import com.davidsantiagoiriarte.presentation.screens.map.BitmapHelper
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

/**
 * A custom cluster renderer for Place objects.
 */
class MarcadorRenderer(
    private val context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<Marcador>
) : DefaultClusterRenderer<Marcador>(context, map, clusterManager) {

    /**
     * The icon to use for each cluster item
     */
    private val iconHome: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(
            context,
            com.google.android.material.R.color.material_blue_grey_900
        )
        BitmapHelper.vectorToBitmap(
            context,
            R.drawable.home,
            color
        )
    }

    private val iconReparto: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(
            context,
            com.google.android.material.R.color.material_blue_grey_900
        )
        BitmapHelper.vectorToBitmap(
            context,
            R.drawable.reparto,
            color
        )
    }

    /**
     * Method called before the cluster item (i.e. the marker) is rendered. This is where marker
     * options should be set
     */
    override fun onBeforeClusterItemRendered(item: Marcador, markerOptions: MarkerOptions) {
        markerOptions.title(item.name)
            .position(item.latLng)
            .icon(if (item.isHomeIcon) iconHome else iconReparto)
    }

    /**
     * Method called right after the cluster item (i.e. the marker) is rendered. This is where
     * properties for the Marker object should be set.
     */
    override fun onClusterItemRendered(clusterItem: Marcador, marker: Marker) {
        marker.tag = clusterItem
    }
}
