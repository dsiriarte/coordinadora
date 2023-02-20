package com.davidsantiagoiriarte.presentation.navigation

sealed class Screen(val route : String){
    object HomeScreen : Screen("home_screen")
    object GuiaScreen : Screen("guia_screen")
}
