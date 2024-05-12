package com.festra.dormitory.navigation

sealed class Screen(
    val route: String
) {
    data object Login: Screen("login")
    data object Register: Screen("register")
    data object Home: Screen("home")
    data object History: Screen("history")
    data object Profile: Screen("profile")
    data object About: Screen("about")
    data object Aturan: Screen("aturan")
}