package com.ayush.jetchat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ayush.jetchat.commons.Constants.AUTH_ROUTE
import com.ayush.jetchat.commons.Constants.ROOT_ROUTE
import com.ayush.jetchat.commons.Screen
import com.ayush.jetchat.presentation.screens.main.MainScreen

@Composable
fun RootNavigationGraph() {

    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        route = ROOT_ROUTE,
        startDestination = AUTH_ROUTE
    ) {
        authNavGraph(navController = navHostController)
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navHostController)
        }
    }

}