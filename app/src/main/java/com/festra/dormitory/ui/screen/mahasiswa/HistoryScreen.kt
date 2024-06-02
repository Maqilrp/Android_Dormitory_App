package com.festra.dormitory.ui.screen.mahasiswa

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.festra.dormitory.R
import com.festra.dormitory.ui.component.BottomBarNavigationComponent
import com.festra.dormitory.ui.theme.DormitoryAppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HistoryScreen(navController: NavController) {
    // data store
//    val dataStore = SettingsDataStore(LocalContext.current)
//    val showList by dataStore.layoutFlow.collectAsState(true)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "History") },
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
            BottomBarNavigationComponent(navController = navController, selectedIconIndex = 0)
        }
    ) { paddingValues ->
        HistoryContent(modifier = Modifier.padding(paddingValues), navController)
    }
}

@Composable
fun HistoryContent(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()) // Menambahkan verticalScroll di sini
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val items = listOf(
            HistoryItem(
                imageRes = R.drawable.perizinan,
                title = "Perizinan",
                description = "Users dapat menggunakan fitur ini untuk menguruskan perizinan di area asrama.",
                destination = "perizinan"
            ),
            HistoryItem(
                imageRes = R.drawable.laundry,
                title = "Laundry",
                description = "Fitur ini menyediakan seluruh informasi pengelolaan laundry para penghuni asrama.",
                destination = "laundry"
            ),
            HistoryItem(
                imageRes = R.drawable.airminum,
                title = "Air Minum",
                description = "Fitur ini membantu pengelolaan asrama agar dapat memenuhi persediaan air minum penghuni.",
                destination = "air_minum"
            ),
            HistoryItem(
                imageRes = R.drawable.listrik,
                title = "Listrik",
                description = "Membantu pengguna untuk mengontrol dan menambah token listrik untuk listrik asrama.",
                destination = "listrik"
            ),
            HistoryItem(
                imageRes = R.drawable.paket,
                title = "Paket",
                description = "Informasi pengiriman paket masuk dan keluar asrama, serta memastikan keamanan dan keakuratan.",
                destination = "paket"
            )
        )

        items.forEach { item ->
            HistoryCard(item = item, navController = navController)
        }
    }
}

data class HistoryItem(
    val imageRes: Int,
    val title: String,
    val description: String,
    val destination: String
)

@Composable
fun HistoryCard(item: HistoryItem, navController: NavController) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(item.destination) }
            .background(Color.White)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.description,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Button(
                    onClick = { navController.navigate(item.destination) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Go ${item.title}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryPreview() {
    DormitoryAppTheme {
        HistoryScreen(rememberNavController())
    }
}