package com.festra.dormitory.ui.screen.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocalLaundryService
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.festra.dormitory.ui.screen.BottomNavigationItem
import com.festra.dormitory.ui.screen.admin.AdminMenuButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAdmin(navController: NavController) {
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

    var selectedIconIndex by rememberSaveable { mutableIntStateOf(2) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Admin") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
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
                        label = { Text(text = item.title) },
                        alwaysShowLabel = false,
                        icon = {
                            Icon(
                                imageVector = if (index == selectedIconIndex) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        HomeContent(modifier = Modifier.padding(paddingValues), navController = navController)
    }
}

@Composable
fun HomeContent(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hi Admin!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = "Welcome back to your panel.",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "ADMIN MENU",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        AdminMenuButton(
            label = "Users",
            icon = Icons.Default.Person,
            onClick = { navController.navigate("Usersadmin") }
        )
        AdminMenuButton(
            label = "GEDUNG",
            icon = Icons.Default.Apartment,
            onClick = { navController.navigate("Gedungadmin") }
        )
        AdminMenuButton(
            label = "PERIZINAN",
            icon = Icons.Default.Assignment,
            onClick = { navController.navigate("Perizinanadmin") }
        )
        AdminMenuButton(
            label = "LAUNDRY",
            icon = Icons.Default.LocalLaundryService,
            onClick = { navController.navigate("Laundryadmin") }
        )
        AdminMenuButton(
            label = "AIR MINUM",
            icon = Icons.Default.LocalDrink,
            onClick = { navController.navigate("airminumadmin") }
        )
        AdminMenuButton(
            label = "LISTRIK",
            icon = Icons.Default.FlashOn,
            onClick = { navController.navigate("Listrikadmin") }
        )
        AdminMenuButton(
            label = "PAKET",
            icon = Icons.Default.LocalShipping,
            onClick = { navController.navigate("Paketadmin") }
        )
        AdminMenuButton(
            label = "HISTORY",
            icon = Icons.Default.History,
            onClick = { navController.navigate("history") }
        )
    }
}

@Composable
fun AdminMenuButton(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
