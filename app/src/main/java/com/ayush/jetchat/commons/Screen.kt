package com.ayush.jetchat.commons

sealed class Screen(
    val route: String
) {
    object SignInScreen : Screen("sign_in_screen")
    object SignUpScreen : Screen("sign_up_screen")
    object MainScreen : Screen("main_screen")
    object DetailsScreen: Screen("details_screen")
}
