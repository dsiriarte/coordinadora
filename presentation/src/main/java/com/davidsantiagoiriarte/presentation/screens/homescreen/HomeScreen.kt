package com.davidsantiagoiriarte.presentation.screens.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.davidsantiagoiriarte.presentation.R
import com.davidsantiagoiriarte.presentation.fakeGuias
import com.davidsantiagoiriarte.presentation.model.ViewGuia
import com.davidsantiagoiriarte.presentation.navigation.Screen
import com.davidsantiagoiriarte.presentation.screens.CoordinadoraTopHeader
import com.davidsantiagoiriarte.presentation.screens.ErrorMessage
import com.davidsantiagoiriarte.presentation.screens.NoInternetComponent
import com.davidsantiagoiriarte.presentation.ui.theme.*

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val guias by viewModel.guias.collectAsState()
    val mostrarMensajeError by viewModel.mostrarError.collectAsState()
    val mensajeError by viewModel.mensajeError.collectAsState()
    val noInternetFlag by viewModel.noInternetFlag.collectAsState()

    if (mostrarMensajeError) {
        ErrorMessage(errorMessage = mensajeError) {
            viewModel.mostrarError.value = false
        }
    }
    Column {
        if (noInternetFlag) {
            NoInternetComponent()
        }
        Header()
        Column {
            SearchBar() {
                viewModel.buscarGuiasCliente(it)
            }
            Divider()
            BarraFiltros(
                guias,
                { viewModel.ordenarListaPorFecha() },
                { viewModel.filtrarPorEstado(it) })
            ItemList(guias) {
                navController.navigate("${Screen.GuiaScreen.route}/$it")
            }
        }
    }
}


@Composable
fun Header() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CoordinadoraTopHeader()
        Text(
            text = stringResource(id = R.string.titulo),
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = stringResource(id = R.string.instrucciones),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun BarraFiltros(
    guias: List<ViewGuia>,
    onSortClicked: () -> Unit,
    onFilterClicked: (estado: String?) -> Unit
) {
    var openFilterDialog by remember {
        mutableStateOf(false)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "${stringResource(id = R.string.envios_encontrados)} (${guias.size})",
            textAlign = TextAlign.Start,
        )
        Row() {
            IconButton(onClick = { openFilterDialog = true }) {
                Icon(painter = painterResource(id = R.drawable.filter), contentDescription = null)
            }
            IconButton(onClick = { onSortClicked() }) {
                Icon(painter = painterResource(id = R.drawable.sort), contentDescription = null)
            }
        }
    }

    val estados = guias.distinctBy { it.estadoGuia }.map { Pair(it.estadoGuia, it.colorBackground) }

    if (openFilterDialog) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
                openFilterDialog = false
            },
            title = {
                Text(text = "Selecciona un filtro")
            },
            text = {
                Column {
                    estados.forEach {
                        Button(
                            onClick = {
                                onFilterClicked(it.first)
                                openFilterDialog = false
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = it.second)
                        ) {
                            Text(text = it.first)
                        }
                    }
                    Button(
                        onClick = {
                            onFilterClicked(null)
                            openFilterDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
                    ) {
                        Text(text = "Borrar filtros")
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        openFilterDialog = false
                    }) {
                    Text("Cerrar")
                }
            }
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    onSearchClicked: (searchQuery: String) -> Unit
) {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column {
        Text(
            text = stringResource(id = R.string.buscar_envios),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            value = text,
            onValueChange = { newText ->
                if ((newText.text.isEmpty() || newText.text.all { it.isDigit() }) && newText.text.length <= 15) {
                    text = newText
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    keyboardController?.hide()
                    onSearchClicked(text.text)
                }) {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
            },
            placeholder = {
                Text(stringResource(id = R.string.placeholder_search))
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    onSearchClicked(text.text)
                }
            ),
            modifier = Modifier
                .heightIn(min = 56.dp)
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun ItemList(guias: List<ViewGuia>, onItemClicked: (numeroGuia: String) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(guias) { guia ->
            Item(guia, onItemClicked)
        }
    }
}

@Composable
fun Item(guia: ViewGuia, onItemClicked: (numeroGuia: String) -> Unit) {
    Column(modifier = Modifier.clickable {
        onItemClicked(guia.guia)
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(intrinsicSize = IntrinsicSize.Max)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .background(guia.colorBackground)
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text(
                    text = guia.estadoGuia,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Icon(
                    painterResource(id = guia.iconoGuia),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
            Column(
                modifier = Modifier
                    .background(itemBackground)
                    .weight(2f)
                    .fillMaxHeight()
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = guia.fechaEnvio,
                        modifier = Modifier
                            .background(fechaBackground)
                            .padding(4.dp),
                        textAlign = TextAlign.Right
                    )

                }
                Column {
                    Text(
                        text = stringResource(id = R.string.guia_de_rastreo),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(
                        text = guia.guia,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier
                .background(itemBackground)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.destinatario),
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = guia.destinatario.nombre,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}
