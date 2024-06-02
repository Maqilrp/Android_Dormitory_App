package com.festra.dormitory.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.festra.dormitory.ui.screen.mahasiswa.BottomNavigationItem


val itemsAdmin = listOf(
    BottomNavigationItem(
        title = "Home Admin",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    BottomNavigationItem(
        title = "Profile Admin",
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle
    ),
)


@Composable
fun BottomBarAdminNavigationComponent(
    navController: NavController,
    selectedIconIndex: Int?
){

    var selectedIconNavigation by rememberSaveable {
        mutableIntStateOf(selectedIconIndex ?: -1)
    }

    NavigationBar {
        itemsAdmin.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIconIndex == index,
                onClick = {
                    selectedIconNavigation = index
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