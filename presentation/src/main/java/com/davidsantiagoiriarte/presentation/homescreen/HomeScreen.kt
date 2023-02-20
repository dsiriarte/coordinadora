package com.davidsantiagoiriarte.presentation.homescreen

import androidx.compose.foundation.background
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
import com.davidsantiagoiriarte.presentation.R
import com.davidsantiagoiriarte.presentation.fakeGuias
import com.davidsantiagoiriarte.presentation.model.ViewGuia
import com.davidsantiagoiriarte.presentation.ui.theme.*

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val guias by viewModel.guias.collectAsState()
    Column {
        Header()
        Column {
            SearchBar() {
                viewModel.buscarGuiasCliente(it)
            }
            Divider()
            BarraFiltros(enviosEncontrados = guias.size) { viewModel.ordenarListaPorFecha() }
            ItemList(guias)
        }
    }
}

@Composable
fun Header() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = R.drawable.logo_coordinadora),
            contentDescription = null
        )
        Text(
            text = "Tracking de Envio",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Escriba su numero de cedula para buscar sus guias asociadas.",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun BarraFiltros(enviosEncontrados: Int, onSortClicked: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Envios encontrados ($enviosEncontrados)",
            textAlign = TextAlign.Start,
        )
        Row() {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.filter), contentDescription = null)
            }
            IconButton(onClick = { onSortClicked() }) {
                Icon(painter = painterResource(id = R.drawable.sort), contentDescription = null)
            }
        }
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
            text = "Buscar envios",
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
fun ItemList(guias: List<ViewGuia>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(guias) { guia ->
            Item(guia)
        }
    }
}

@Composable
fun Item(guia: ViewGuia) {
    Column {
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
                        text = "Guia de rastreo",
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
                text = "Destinatario:",
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = guia.destinatario.nombre,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    CoordinadoraTheme {
        HomeScreen()
    }
}