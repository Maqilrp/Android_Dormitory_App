package com.festra.dormitory.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.festra.dormitory.ui.screen.AboutScreen
import com.festra.dormitory.ui.screen.AirminumScreen
import com.festra.dormitory.ui.screen.AturanScreen
import com.festra.dormitory.ui.screen.HistoryScreen
import com.festra.dormitory.ui.screen.HomeScreen
import com.festra.dormitory.ui.screen.LaundryScreen
import com.festra.dormitory.ui.screen.ListrikScreen
import com.festra.dormitory.ui.screen.Login
import com.festra.dormitory.ui.screen.PaketScreen
import com.festra.dormitory.ui.screen.PerizinanScreen
import com.festra.dormitory.ui.screen.ProfileScreen
import com.festra.dormitory.ui.screen.RegisterScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(
            route = Screen.Login.route
        ) {
            Login(navController)
        }
        composable(
            route = Screen.Register.route
        ) {
            RegisterScreen(navController)
        }
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.History.route
        ) {
            HistoryScreen(navController)
        }
        composable(
            route = Screen.Profile.route
        ) {
            ProfileScreen(navController)
        }
        composable(
            route = Screen.About.route
        ) {
            AboutScreen(navController)
        }
        composable(
            route = Screen.Aturan.route
        ) {
            AturanScreen(navController)
        }
        composable(
            route = Screen.Perizinan.route
        ) {
            PerizinanScreen(navController)
        }
        composable(
            route = Screen.Laundry.route
        ) {
            LaundryScreen(navController)
        }
        composable(
            route = Screen.Airminum.route
        ) {
            AirminumScreen(navController)
        }
        composable(
            route = Screen.Listrik.route
        ) {
            ListrikScreen(navController)
        }
        composable(
            route = Screen.Paket.route
        ) {
            PaketScreen(navController)
        }
    }
}