package com.festra.dormitory.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.festra.dormitory.ui.screen.RegisterScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Register.route
    ) {
        composable(
            route = Screen.Register.route
        ) {
            RegisterScreen(navController)
        }
    }
}