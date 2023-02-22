package com.davidsantiagoiriarte.presentation.screens.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.davidsantiagoiriarte.presentation.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.davidsantiagoiriarte.presentation.screens.map.marcador.Marcador
import com.davidsantiagoiriarte.presentation.screens.map.marcador.MarcadorExtra
import com.davidsantiagoiriarte.presentation.screens.map.marcador.MarcadorRenderer
import com.davidsantiagoiriarte.presentation.screens.map.marcador.map
import com.google.maps.android.clustering.ClusterManager

const val MARCADOR1_EXTRA_NAME = "MARCADOR1_EXTRA_NAME"
const val MARCADOR2_EXTRA_NAME = "MARCADOR2_EXTRA_NAME"

class MapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_fragment) as? SupportMapFragment

        val marcador1 = intent.extras?.getSerializable(MARCADOR1_EXTRA_NAME) as? MarcadorExtra
        val marcador2 = intent.extras?.getSerializable(MARCADOR2_EXTRA_NAME) as? MarcadorExtra

        val marcadores = arrayListOf<Marcador>()
        marcador1?.map()?.let {
            marcadores.add(it)
        }
        marcador2?.map()?.let {
            marcadores.add(it)
        }

        mapFragment?.getMapAsync { googleMap ->
            // Ensure all places are visible in the map
            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()
                marcadores.forEach { bounds.include(it.latLng) }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 20))
            }

            //addMarkers(googleMap)
            addClusteredMarkers(googleMap, marcadores)

            // Set custom info window adapter
            // googleMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(this))
        }
    }

    /**
     * Adds markers to the map with clustering support.
     */
    private fun addClusteredMarkers(googleMap: GoogleMap, marcadores: List<Marcador>) {
        // Create the ClusterManager class and set the custom renderer
        val clusterManager = ClusterManager<Marcador>(this, googleMap)
        clusterManager.renderer =
            MarcadorRenderer(
                this,
                googleMap,
                clusterManager
            )

        // Add the places to the ClusterManager
        clusterManager.addItems(marcadores)
        clusterManager.cluster()

        // Show polygon
        clusterManager.setOnClusterItemClickListener { item ->
            addCircle(googleMap, item)
            return@setOnClusterItemClickListener false
        }

        // When the camera starts moving, change the alpha value of the marker to translucent
        googleMap.setOnCameraMoveStartedListener {
            clusterManager.markerCollection.markers.forEach { it.alpha = 0.3f }
            clusterManager.clusterMarkerCollection.markers.forEach { it.alpha = 0.3f }
        }

        googleMap.setOnCameraIdleListener {
            // When the camera stops moving, change the alpha value back to opaque
            clusterManager.markerCollection.markers.forEach { it.alpha = 1.0f }
            clusterManager.clusterMarkerCollection.markers.forEach { it.alpha = 1.0f }

            // Call clusterManager.onCameraIdle() when the camera stops moving so that re-clustering
            // can be performed when the camera stops moving
            clusterManager.onCameraIdle()
        }
    }

    private var circle: Circle? = null

    /**
     * Adds a [Circle] around the provided [item]
     */
    private fun addCircle(googleMap: GoogleMap, item: Marcador) {
        circle?.remove()
        circle = googleMap.addCircle(
            CircleOptions()
                .center(item.latLng)
                .radius(1000.0)
                .fillColor(
                    ContextCompat.getColor(
                        this,
                        com.google.android.material.R.color.design_default_color_primary
                    )
                )
                .strokeColor(
                    ContextCompat.getColor(
                        this,
                        com.google.android.material.R.color.primary_dark_material_dark
                    )
                )
        )
    }

}
