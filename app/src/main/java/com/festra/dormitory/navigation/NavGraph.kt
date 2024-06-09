package com.festra.dormitory.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.festra.dormitory.ui.screen.AboutScreen
import com.festra.dormitory.ui.screen.AirminumScreen
import com.festra.dormitory.ui.screen.AturanScreen
import com.festra.dormitory.ui.screen.DetailScreen
import com.festra.dormitory.ui.screen.HistoryScreen
import com.festra.dormitory.ui.screen.HomeScreen
import com.festra.dormitory.ui.screen.LaundryScreen
import com.festra.dormitory.ui.screen.ListrikScreen
import com.festra.dormitory.ui.screen.Login
import com.festra.dormitory.ui.screen.PaketScreen
import com.festra.dormitory.ui.screen.PerizinanScreen
import com.festra.dormitory.ui.screen.ProfileScreen
import com.festra.dormitory.ui.screen.RegisterScreen
import com.festra.dormitory.ui.screen.admin.Admin_AirMinum
import com.festra.dormitory.ui.screen.admin.Admin_Gedung
import com.festra.dormitory.ui.screen.admin.Admin_Laundry
import com.festra.dormitory.ui.screen.admin.Admin_Listrik
import com.festra.dormitory.ui.screen.admin.Admin_Paket
import com.festra.dormitory.ui.screen.admin.Admin_Perizinan
import com.festra.dormitory.ui.screen.admin.Admin_Users
import com.festra.dormitory.ui.screen.admin.Admin_mahasiswa
import com.festra.dormitory.ui.screen.admin.HomeAdmin

@Composable
fun SetupNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Admin_mahasiswa.route
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
        composable(
                route = Screen.HomeAdmin.route
                ) {
            HomeAdmin(navController)
        }
        composable(
            route = Screen.Admin_mahasiswa.route
        ) {
            Admin_mahasiswa(navController)
        }
        composable(
            route = Screen.Admin_AirMinum.route
        ) {
            Admin_AirMinum(navController)
        }
        composable(
            route = Screen.Admin_Users.route
        ) {
            Admin_Users(navController)
        }
        composable(
            route = Screen.Admin_Gedung.route
        ) {
            Admin_Gedung(navController)
        }
        composable(
            route = Screen.Admin_Perizinan.route
        ) {
            Admin_Perizinan(navController)
        }
        composable(
            route = Screen.Admin_Laundry.route
        ) {
            Admin_Laundry(navController)
        }
        composable(
            route = Screen.Admin_Listrik.route
        ) {
            Admin_Listrik(navController)
        }
        composable(
            route = Screen.Admin_Paket.route
        ) {
            Admin_Paket(navController)
        }
        composable(
            route = Screen.Detail.route + "/{title}",
            arguments = listOf(navArgument("title") { type = NavType.StringType })
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            title?.let { DetailScreen(navController = navController, title = it) }
        }
    }
}