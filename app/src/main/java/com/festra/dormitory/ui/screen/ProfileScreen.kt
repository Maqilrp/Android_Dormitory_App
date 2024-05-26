package com.festra.dormitory.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.festra.dormitory.navigation.Screen
import com.festra.dormitory.ui.theme.DormitoryAppTheme


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProfileScreen(navController: NavController) {
    // data store
//    val dataStore = SettingsDataStore(LocalContext.current)
//    val showList by dataStore.layoutFlow.collectAsState(true)

    val items = listOf(
        BottomNavigationItem(
            title = "history",
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart
        ),
        BottomNavigationItem(
            title = "home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            title = "profile",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle
        ),
    )

    var selectedIconIndex by rememberSaveable {
        mutableIntStateOf(2)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Profile") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceBright,
                    titleContentColor = MaterialTheme.colorScheme.primary

                ),
                actions = {
//                    IconButton(onClick = {
//                        CoroutineScope(Dispatchers.IO).launch {
//                            dataStore.saveLayout(!showList)
//                        }
//                    })
//                    {
//                        Icon(
//                            painter = painterResource(
//                                if (showList) R.drawable.baseline_grid_view_24
//                                else R.drawable.baseline_view_list_24
//                            ),
//                            contentDescription = stringResource(
//                                if (showList) R.string.grid
//                                else R.string.list
//                            ),
//                            tint = MaterialTheme.colorScheme.primary
//                        )
//                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIconIndex == index,
                        onClick = {
                            selectedIconIndex = index
                            navController.navigate(item.title)
                        },
                        label = {
                            Text(text = item.title)
                        },
                        alwaysShowLabel = false,
                        icon = {
                            Icon(
                                imageVector = if (index == selectedIconIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        ProfileContent(modifier = Modifier.padding(paddingValues), navController)
    }
}

@Composable
fun UserProfileScreen(navController: NavController) {
    ProfileScreen(navController = navController)
}

@Composable
fun ProfileContent(modifier: Modifier, navController: NavController) {
    var fullName by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var nim by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var buildingNumber by remember { mutableStateOf(TextFieldValue("")) }
    var roomNumber by remember { mutableStateOf(TextFieldValue("")) }
    var photo by remember { mutableStateOf<Painter?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Placeholder for profile image
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .clickable { /* Handle photo selection */ },
            contentAlignment = Alignment.Center
        ) {
            if (photo == null) {
                Text(text = "Foto Mahasiswa/i", color = Color.DarkGray)
            } else {
                Image(painter = photo!!, contentDescription = "Foto Mahasiswa/i")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Input fields
        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Nama Lengkap") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = nim,
            onValueChange = { nim = it },
            label = { Text("NIM") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Nomor Telepon") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = buildingNumber,
            onValueChange = { buildingNumber = it },
            label = { Text("Nomor Gedung") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = roomNumber,
            onValueChange = { roomNumber = it },
            label = { Text("Nomor Kamar") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Submit and Cancel buttons
        Row {
            Button(
                onClick = { /* Handle submit */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
            ) {
                Text("Submit")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { navController.navigate(Screen.Home.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Cancel")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    DormitoryAppTheme {
        ProfileScreen(rememberNavController())
    }
}

//data class BottomNavigationItem(
//    val title: String,
//    val selectedIcon: ImageVector,
//    val unselectedIcon: ImageVector
//)