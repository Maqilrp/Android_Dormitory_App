package com.festra.dormitory.navigation

sealed class Screen(
    val route: String
) {
    data object Register: Screen("register")
}