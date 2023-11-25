package com.ayush.jetchat.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ayush.jetchat.commons.Constants.AUTH_ROUTE
import com.ayush.jetchat.commons.Screen
import com.ayush.jetchat.presentation.screens.auth.signin.SignInScreen
import com.ayush.jetchat.presentation.screens.auth.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavController) {

    navigation(
        route = AUTH_ROUTE,
        startDestination = Screen.SignInScreen.route
    ) {
        composable(route = Screen.SignInScreen.route) {
            SignInScreen(navController = navController)
        }

        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }
    }

}