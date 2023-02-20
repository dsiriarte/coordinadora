package com.davidsantiagoiriarte.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidsantiagoiriarte.presentation.model.ViewGuia
import com.davidsantiagoiriarte.presentation.ui.theme.*

@Composable
fun HomeScreen() {
    Column {
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
        Column {
            Text(
                text = "Buscar envios",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(16.dp)
            )
            SearchBar()
            Divider()
            BarraFiltros(enviosEncontrados = 4)
            ItemList(guias = fakeGuias)
        }
    }
}

@Composable
fun BarraFiltros(enviosEncontrados: Int) {
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
            Icon(painter = painterResource(id = R.drawable.filter), contentDescription = null)
            Icon(painter = painterResource(id = R.drawable.sort), contentDescription = null)
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        trailingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        placeholder = {
            Text(stringResource(id = R.string.placeholder_search))
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth()
    )
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
                Text(text = guia.estadoGuia, color = Color.White)
                Icon(
                    painterResource(id = guia.iconoGuia),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
            Column(
                modifier = Modifier
                    .background(Color.LightGray)
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
                            .background(Color.Gray)
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
                .background(Color.LightGray)
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