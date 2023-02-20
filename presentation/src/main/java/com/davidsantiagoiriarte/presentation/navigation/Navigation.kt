package com.davidsantiagoiriarte.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.davidsantiagoiriarte.presentation.screens.guiascreen.GuiaScreen
import com.davidsantiagoiriarte.presentation.screens.homescreen.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.GuiaScreen.route+"/{numeroGuia}",
            arguments = listOf(navArgument("numeroGuia"){
                type=
            NavType.StringType}
            )
        )
        {
            GuiaScreen(navController = navController,it.arguments?.getString("numeroGuia"))
        }
    }
}
