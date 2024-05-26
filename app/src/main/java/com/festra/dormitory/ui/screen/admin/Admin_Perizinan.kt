package com.festra.dormitory.ui.screen.admin




import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
fun Admin_Perizinan(navController: NavController) {
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
    var showPerizinanContent by rememberSaveable { mutableStateOf(false) }

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
        AdminPerizinanContent(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            showPerizinanContent = showPerizinanContent,
            onPerizinanClick = { showPerizinanContent = !showPerizinanContent }
        )
    }
}

@Composable
fun AdminPerizinanContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    showPerizinanContent: Boolean,
    onPerizinanClick: () -> Unit
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
            text = "PERIZINAN",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        AdminMenuButton(
            label = "Perizinan",
            icon = Icons.Default.Assignment,
            onClick = onPerizinanClick

        )

        if (showPerizinanContent) {
            Spacer(modifier = Modifier.height(16.dp))
            PerizinanContent()
        }
    }
}

@Composable
fun AdminMenuPerizinanButton(label: String, icon: ImageVector, onClick: () -> Unit) {
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
fun PerizinanContent() {
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.paket), // ganti dengan resource gambar profil
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Gray, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Jacob Jones",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "6706220090",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "IZIN",
                        color = Color.Green,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Column {
                    Text("Status", fontSize = 14.sp, fontWeight = FontWeight.Light)
                    Text("Phone number", fontSize = 14.sp, fontWeight = FontWeight.Light)
                    Text("Gedung", fontSize = 14.sp, fontWeight = FontWeight.Light)
                    Text("Alasan", fontSize = 14.sp, fontWeight = FontWeight.Light)
                }
            }
        }
    }
}
