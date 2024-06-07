package com.festra.dormitory.ui.screen.mahasiswa

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun AturanScreen(navController: NavController) {
    // data store
//    val dataStore = SettingsDataStore(LocalContext.current)
//    val showList by dataStore.layoutFlow.collectAsState(true)

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = { Text(text = "Aturan") },
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
            BottomBarNavigationComponent(navController = navController, selectedIconIndex = null)
        }
    ) { paddingValues ->
        AturanContent(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun AturanContent(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp) // Use CardDefaults.cardElevation
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Simplify your\n" +
                        "dorm management \n" +
                        "with Festra",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.logo5050_1),
                contentDescription = "Festra",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "Festra dapat menyediakan layanan berupa platform atau website yang memudahkan para penghuni asrama untuk mengakses informasi seputar asrama, termasuk mengelola fasilitas asrama dan berkomunikasi dengan pengelola asrama.\n" +
                        "\n" +
                        "Platform yang disediakan oleh Festra dapat berisi informasi terkait jadwal kegiatan asrama, informasi terkait fasilitas asrama, serta informasi penting lainnya seperti pengumuman atau aturan-aturan yang berlaku di dalam asrama. Dengan platform ini, para penghuni asrama dapat dengan mudah mengakses informasi tersebut kapan saja dan di mana saja tanpa perlu bertemu langsung dengan pengelola asrama.\n" +
                        "\n" +
                        "Selain itu, platform yang disediakan oleh Festra juga dapat dilengkapi dengan fitur-fitur lain yang dapat memudahkan para penghuni asrama dalam mengelola kebutuhan mereka di dalam asrama. Misalnya, fitur reservasi atau pemesanan fasilitas asrama seperti ruangan rapat, lapangan olahraga, atau fasilitas lainnya yang dapat digunakan oleh para penghuni asrama. Dengan adanya fitur tersebut, penghuni asrama dapat dengan mudah mengelola dan menggunakan fasilitas asrama tanpa perlu menghubungi pengelola asrama secara langsung.\n" +
                        "\n" +
                        "Selain itu, platform yang disediakan oleh Festra juga dapat dilengkapi dengan fitur komunikasi antara para penghuni asrama dan pengelola asrama. Fitur ini memungkinkan para penghuni asrama untuk mengajukan pertanyaan, keluhan, atau permintaan bantuan kepada pengelola asrama secara online. Hal ini dapat memudahkan penghuni asrama dalam berkomunikasi dengan pengelola asrama, terutama jika mereka tidak bisa bertemu langsung atau ketika pengelola asrama sibuk.\n" +
                        "\n" +
                        "Dengan menyediakan platform atau website yang memudahkan para penghuni asrama dalam mengakses informasi dan mengelola fasilitas asrama, Festra dapat membantu penghuni asrama untuk lebih efektif dan efisien dalam mengelola kebutuhan mereka di dalam asrama. Sehingga, Festra memiliki potensi untuk menjadi salah satu penyedia layanan asrama yang terpercaya dan terdepan.",
                textAlign = TextAlign.Justify,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun AturanPreview() {
    DormitoryAppTheme {
        AturanScreen(rememberNavController())
    }
}