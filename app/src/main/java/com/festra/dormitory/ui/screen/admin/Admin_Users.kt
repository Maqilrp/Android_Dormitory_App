package com.festra.dormitory.ui.screen.admin


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.festra.dormitory.R
import com.festra.dormitory.ui.screen.BottomNavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Admin_Users(navController: NavController) {
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

    var selectedIconIndex by rememberSaveable { mutableStateOf(2) }
    var showUsersContent by rememberSaveable { mutableStateOf(false) }

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
        AdminUsersContent(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            showUsersContent = showUsersContent,
            onUsersClick = { showUsersContent = !showUsersContent }
        )
    }
}

@Composable
fun AdminUsersContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    showUsersContent: Boolean,
    onUsersClick: () -> Unit
) {
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
            text = "USERS",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        AdminMenuButton(
            label = "Users",
            icon = Icons.Default.Person,
            onClick = onUsersClick

        )

        if (showUsersContent) {
            Spacer(modifier = Modifier.height(16.dp))
            UsersContent()
        }
    }
}

@Composable
fun AdminMenuUsersButton(label: String, icon: ImageVector, onClick: () -> Unit) {
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

@Composable
fun UsersContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Header
                Text(
                    text = "Air Minum Fitur",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Info Rows
                InfoRow(label = "info:", value = "info")
                InfoRow(label = "info:", value = "info")
                InfoRow(label = "Status:", value = "info", hasDropdown = true)

                // Buttons Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { /* Accept action */ }) {
                        Text("Accept")
                    }
                    Button(onClick = { /* Decline action */ }) {
                        Text("Decline")
                    }
                    Button(onClick = { /* Detail action */ }) {
                        Text("Detail")
                    }
                }
            }
        }
    }
}
