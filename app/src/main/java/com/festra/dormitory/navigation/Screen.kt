package com.festra.dormitory.navigation

sealed class Screen(
    val route: String
) {
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object History : Screen("history")
    data object Profile : Screen("profile")
    data object About : Screen("about")
    data object Aturan : Screen("aturan")
    data object Perizinan : Screen("perizinan")
    data object Laundry : Screen("laundry")
    data object Airminum : Screen("airminum")
    data object Listrik : Screen("listrik")
    data object Paket : Screen("paket")
}