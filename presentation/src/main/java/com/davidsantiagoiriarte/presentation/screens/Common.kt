package com.davidsantiagoiriarte.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.davidsantiagoiriarte.presentation.R

@Composable
fun CoordinadoraTopHeader(
    showBackArrow: Boolean = false,
    onBackArrowPressed: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 4.dp, modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        if (showBackArrow) {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                IconButton(onClick = { onBackArrowPressed() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = null
                    )
                }
                CoordinadoraImage()
                Text(text = "")
            }
        } else {
            CoordinadoraImage()
        }
    }
}

@Composable
fun CoordinadoraImage() {
    Image(
        painter = painterResource(id = R.drawable.logo_coordinadora),
        contentDescription = null,
        contentScale = ContentScale.Inside,
        modifier = Modifier.padding(4.dp)
    )
}
