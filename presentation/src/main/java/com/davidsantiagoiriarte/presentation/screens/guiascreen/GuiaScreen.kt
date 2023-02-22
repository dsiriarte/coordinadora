package com.davidsantiagoiriarte.presentation.screens.guiascreen

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.davidsantiagoiriarte.domain.model.Destinatario
import com.davidsantiagoiriarte.domain.model.Unidad
import com.davidsantiagoiriarte.domain.model.Zonificacion
import com.davidsantiagoiriarte.presentation.model.ViewGuia
import com.davidsantiagoiriarte.presentation.screens.CoordinadoraTopHeader
import com.davidsantiagoiriarte.presentation.screens.NoInternetComponent
import com.davidsantiagoiriarte.presentation.screens.map.MARCADOR1_EXTRA_NAME
import com.davidsantiagoiriarte.presentation.screens.map.MARCADOR2_EXTRA_NAME
import com.davidsantiagoiriarte.presentation.screens.map.MapActivity
import com.davidsantiagoiriarte.presentation.screens.map.marcador.Marcador
import com.davidsantiagoiriarte.presentation.screens.map.marcador.MarcadorExtra
import com.davidsantiagoiriarte.presentation.ui.theme.fechaBackground
import com.davidsantiagoiriarte.presentation.ui.theme.itemBackground
import com.google.android.gms.maps.model.LatLng

@Composable
fun GuiaScreen(
    navController: NavController,
    numeroGuia: String?,
    viewModel: GuiaViewModel = hiltViewModel()
) {
    val guia by viewModel.guia.collectAsState()
    val noInternetFlag by viewModel.noInternetFlag.collectAsState()

    numeroGuia?.let { viewModel.cargarGuia(it) }
    guia?.let { guia ->
        Column(modifier = Modifier.fillMaxSize()) {
            if (noInternetFlag) {
                NoInternetComponent()
            }
            CoordinadoraTopHeader(true, {
                navController.popBackStack()
            })
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = guia.guia,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Divider()
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                Estado(guia)
            }
            Destinatario(guia.destinatario)
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .padding(top = 16.dp, end = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Unidades (${guia.unidades.size})")
            }
            UnidadesList(unidades = guia.unidades)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                if (tieneUbicacionGuia(guia) || tieneUbicacionDestinatario(guia.destinatario.zonificacion)) {
                    val context = LocalContext.current
                    Button(
                        onClick = {
                            context.startActivity(
                                Intent(
                                    context,
                                    MapActivity::class.java
                                ).apply {
                                    if (tieneUbicacionGuia(guia)) {
                                        putExtra(
                                            MARCADOR1_EXTRA_NAME, MarcadorExtra(
                                                guia.estadoGuia,
                                                guia.ubicacionGuiaLat!!,
                                                guia.ubicacionGuiaLng!!,
                                                "",
                                                false
                                            )
                                        )
                                    }
                                    val destinatario = guia.destinatario
                                    if (tieneUbicacionDestinatario(destinatario.zonificacion)) {
                                        putExtra(
                                            MARCADOR2_EXTRA_NAME, MarcadorExtra(
                                                destinatario.nombre,
                                                destinatario.zonificacion.lat!!,
                                                destinatario.zonificacion.lng!!,
                                                destinatario.zonificacion.direccion,
                                                true
                                            )
                                        )
                                    }
                                })
                        },
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(text = "Abrir Mapa de ubicación", modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}

private fun tieneUbicacionGuia(guia: ViewGuia) =
    guia.ubicacionGuiaLat != null && guia.ubicacionGuiaLng != null && guia.ubicacionGuiaLng != 0.0 && guia.ubicacionGuiaLat != 0.0

private fun tieneUbicacionDestinatario(zonificacion: Zonificacion) =
    zonificacion.lat != null && zonificacion.lng != null && zonificacion.lat != 0.0 && zonificacion.lng != 0.0


@Composable
fun Estado(guia: ViewGuia, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(guia.colorBackground)
            .padding(4.dp)
    ) {
        Icon(
            painter = painterResource(id = guia.iconoGuia),
            contentDescription = null,
            tint = Color.White
        )
        Text(
            text = guia.estadoGuia,
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp, end = 16.dp)
        )
    }
}

@Composable
fun Destinatario(destinatario: Destinatario, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.Start, modifier = modifier) {
        Text(
            text = "Información del destinatario:",
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Nombre: ${destinatario.nombre}",
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = "Teléfono: ${destinatario.telefono}",
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = "Dirección: ${destinatario.zonificacion.direccion}",
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = "Ciudad: ${destinatario.zonificacion.ciudad}",
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun UnidadesList(unidades: List<Unidad>, modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = modifier
            .height(300.dp)
            .fillMaxWidth()
    ) {
        items(unidades) { unidad ->
            UnidadItem(unidad)
        }
    }
}

@Composable
fun UnidadItem(unidad: Unidad) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(fechaBackground)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                expanded = !expanded
            }
        ) {
            Text(
                text = unidad.etiqueta1d,
                modifier = Modifier
                    .padding(start = 16.dp, top = 4.dp, bottom = 4.dp)
            )
        }
        if (expanded) {
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .background(itemBackground)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Etiqueta 1D: ${unidad.etiqueta1d}",
                    modifier = Modifier.padding(start = 8.dp, top = 16.dp, end = 8.dp)
                )
                Text(
                    text = "Etiqueta 2D: ${unidad.etiqueta2d}",
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
                )
            }
        }
    }
}

