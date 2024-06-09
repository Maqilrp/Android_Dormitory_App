package com.festra.dormitory.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.festra.dormitory.navigation.Screen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBarNavigationComponent(
    navController: NavController,
    judul: String,
    currentUserRole: String?
){
    CenterAlignedTopAppBar(
        title = {judul},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceBright,
            titleContentColor = MaterialTheme.colorScheme.primary
        ), actions = {
            if (currentUserRole == "admin") {
                IconButton(onClick = { navController.navigate(Screen.AdminProfile.route) }) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Profile"
                    )
                }
            } else{
                IconButton(onClick = { navController.navigate(Screen.Paket.route) }) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Profile"
                    )
                }
            }
        }
    )
}