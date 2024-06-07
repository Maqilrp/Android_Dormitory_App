package com.festra.dormitory.ui.screen.mahasiswa

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.festra.dormitory.R
import com.festra.dormitory.navigation.Screen
import com.festra.dormitory.ui.component.BottomBarNavigationComponent
import com.festra.dormitory.ui.theme.DormitoryAppTheme


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(navController: NavController) {
    // data store
//    val dataStore = SettingsDataStore(LocalContext.current)
//    val showList by dataStore.layoutFlow.collectAsState(true)



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
                    IconButton(onClick = { navController.navigate(Screen.Profile.route) }) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Profile"
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomBarNavigationComponent(navController = navController, selectedIconIndex = 1 )
        }
    ) { paddingValues ->
        HomeContent(modifier = Modifier.padding(paddingValues), navController)
    }
}

@Composable
fun HomeContent(modifier: Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally // Center align horizontally
                ) {
                    Text(
                        text = "Festra",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 24.sp, // You can adjust the font size as needed
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp)) // Space between the title and the caption

                    Text(
                        text = "Aplikasi ini hadir untuk memberikan solusi terhadap segala masalah dan persoalan keperluan asrama yang dianytaranya ialah membantu para penghuni asrama untuk berkomunikasi dengan SR (Senior Residence), helpdesk, dan satpam. Serta menawarkan pembelian token listrik, laundry pakaian, pembelian galon, pengambilan paket, dan mengurus surat izin.",
                        maxLines = 3,
                        textAlign = TextAlign.Center, // Center align the caption text
                        color = Color.Black,
                        fontSize = 10.sp,
                        modifier = Modifier.fillMaxWidth()
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
                text = "Apa Yang Anda Butuhkan ?",
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
//                Fitur Perizinan
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    onClick = { navController.navigate(Screen.Perizinan.route) },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.perizinan),
                            contentDescription = null,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "PERIZINAN",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Users can use this feature to manage permits in the dormitory area.",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { /* Handle GO PERIZINAN */ },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors()
                        ) {
                            Text("GO PERIZINAN")
                        }
                    }
                }
//                Fitur Laundry
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    onClick = { navController.navigate(Screen.Laundry.route) },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.laundry),
                            contentDescription = null,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "LAUNDRY",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "laundry",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors()
                        ) {
                            Text("-")
                        }
                    }
                }
            }
            Row(
                modifier = modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
//                Fitur Air Minum
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    onClick = { navController.navigate(Screen.Airminum.route) },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.airminum),
                            contentDescription = null,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "AIR MINUM",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "air minum",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors()
                        ) {
                            Text("-")
                        }
                    }
                }
//                Fitur Listrik
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    onClick = { navController.navigate(Screen.Listrik.route) },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.listrik),
                            contentDescription = null,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "LISTRIK",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "listrik",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors()
                        ) {
                            Text("-")
                        }
                    }
                }
            }
            Row(
                modifier = modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
//                Fitur Paket
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    onClick = { navController.navigate(Screen.Paket.route) },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.paket),
                            contentDescription = null,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "PAKET",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "paket",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors()
                        ) {
                            Text("-")
                        }
                    }
                }
//                Fitur Histori
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    onClick = { navController.navigate(Screen.History.route) },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_history_24),
                            contentDescription = null,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "HISTORY",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "histori",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors()
                        ) {
                            Text("-")
                        }
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    DormitoryAppTheme {
        HomeScreen(rememberNavController())
    }
}