package com.festra.dormitory.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.festra.dormitory.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBarAdmin(
    navController: NavController,
    judul: String,
) {
    val showMenu = remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()

    CenterAlignedTopAppBar(
        title = { Text(judul) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            IconButton(onClick = { showMenu.value = !showMenu.value }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile"
                )
            }
            DropdownMenu(
                expanded = showMenu.value,
                onDismissRequest = { showMenu.value = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Profile") },
                    onClick = {
                        showMenu.value = false
                        navController.navigate(Screen.AdminProfile.route)
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                HorizontalDivider()
                DropdownMenuItem(
                    text = { Text("Logout") },
                    onClick = {
                        showMenu.value = false
                        auth.signOut()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    )
}
