package com.festra.dormitory.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.festra.dormitory.R
import com.festra.dormitory.navigation.Screen


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(navController: NavController){
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
        mutableIntStateOf(1)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Home") },
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
                    IconButton(onClick = { navController.navigate(Screen.Profile.route)}) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Profile"
                        )
                    }
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
    ) {
        paddingValues ->
        HomeContent(modifier = Modifier.padding(paddingValues), navController)
    }
}

@Composable
fun HomeContent(modifier: Modifier, navController: NavController){
   Box(
       modifier = modifier
           .fillMaxSize()
           .verticalScroll(rememberScrollState())
   ){
       Column(
//           modifier = modifier
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           // Text Header
           Text(
               text = "Mempermudah Hidup Di Asrama",
               textAlign = TextAlign.Center,
               fontWeight = FontWeight.Bold,
               fontSize = 20.sp,
               modifier = modifier.padding(1.dp)
           )

           // gambar asrama
           Box {
               Image(
                   modifier = Modifier.clip(shape = RoundedCornerShape(16.dp)),
                   painter = painterResource(id = R.drawable.gambar_asrama),
                   contentDescription = "Asrama",
               )

               Column {
                   Text(
                       text = "Festra",
                       fontWeight = FontWeight.Bold,
                       color = Color.Black,
                   )

                   Text(
                       text ="Aplikasi ini hadir untuk memberikan solusi terhadap segala masalah dan persoalan keperluan asrama yang dianytaranya ialah membantu para penghuni asrama untuk berkomunikasi dengan SR (Senior Residence), helpdesk, dan satpam. Serta menawarkan pembelian token listrik, laundry pakaian, pembelian galon, pengambilan paket, dan mengurus surat izin.",
                       maxLines = 3,
                       textAlign = TextAlign.Justify,
                       color = Color.Black,
                       fontSize = 10.sp,
                   )

                   Row(
                       modifier = modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.SpaceAround

                   ) {
                       Button(onClick = { navController.navigate(Screen.About.route) }) {
                           Text(text = "Profil Asrama")
                       }
                       Button(onClick = { navController.navigate(Screen.Aturan.route) }) {
                           Text(text = "Aturan Asrama")
                       }
                   }
               }
           }

           Text(
               text = "Apa Yang Anda Butuhkan",
               fontWeight = FontWeight.Bold
           )

           Row(
               modifier = modifier.fillMaxSize(),
               horizontalArrangement = Arrangement.SpaceAround
           ) {
               ElevatedCard(
                   elevation = CardDefaults.cardElevation(
                       defaultElevation = 6.dp
                   ),
                   onClick = {  navController.navigate(Screen.Perizinan.route) },
                   colors = CardDefaults.cardColors(
                       containerColor = MaterialTheme.colorScheme.surfaceVariant,
                   ),
                   modifier = Modifier
                       .size(width = 150.dp, height = 150.dp)
               ) {
                   Text(text = "Perizinan")
               }

               ElevatedCard(
                   elevation = CardDefaults.cardElevation(
                       defaultElevation = 6.dp
                   ),
                   onClick = { /*TODO*/ },
                   colors = CardDefaults.cardColors(
                       containerColor = MaterialTheme.colorScheme.surfaceVariant,
                   ),
                   modifier = Modifier
                       .size(width = 150.dp, height = 150.dp)
               ) {
                   Text(text = "Laundry")
               }

           }

           Row(
               modifier = modifier.fillMaxSize(),
               horizontalArrangement = Arrangement.SpaceAround
           ) {
               ElevatedCard(
                   elevation = CardDefaults.cardElevation(
                       defaultElevation = 6.dp
                   ),
                   onClick = { /*TODO*/ },
                   colors = CardDefaults.cardColors(
                       containerColor = MaterialTheme.colorScheme.surfaceVariant,
                   ),
                   modifier = Modifier
                       .size(width = 150.dp, height = 150.dp)
               ) {
                   Text(text = "Air Minum")
               }

               ElevatedCard(
                   elevation = CardDefaults.cardElevation(
                       defaultElevation = 6.dp
                   ),
                   onClick = { /*TODO*/ },
                   colors = CardDefaults.cardColors(
                       containerColor = MaterialTheme.colorScheme.surfaceVariant,
                   ),
                   modifier = Modifier
                       .size(width = 150.dp, height = 150.dp)
               ) {
                   Text(text = "Listrik")
               }

           }

           Row(
               modifier = modifier.fillMaxSize(),
               horizontalArrangement = Arrangement.SpaceAround
           ) {
               ElevatedCard(
                   elevation = CardDefaults.cardElevation(
                       defaultElevation = 6.dp
                   ),
                   onClick = { /*TODO*/ },
                   colors = CardDefaults.cardColors(
                       containerColor = MaterialTheme.colorScheme.surfaceVariant,
                   ),
                   modifier = Modifier
                       .size(width = 150.dp, height = 150.dp)
               ) {
                   Text(text = "Paket")
               }

               ElevatedCard(
                   elevation = CardDefaults.cardElevation(
                       defaultElevation = 6.dp
                   ),
                   onClick = { /*TODO*/ },
                   colors = CardDefaults.cardColors(
                       containerColor = MaterialTheme.colorScheme.surfaceVariant,
                   ),
                   modifier = Modifier
                       .size(width = 150.dp, height = 150.dp)
               ) {
                   Text(text = "History")
               }

           }
       }
   }
}