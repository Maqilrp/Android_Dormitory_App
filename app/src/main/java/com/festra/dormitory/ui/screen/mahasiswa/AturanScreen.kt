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
                text = "PERATURAN ASRAMA" ,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Text(
                text = """
        1. Saling menghormati dan menjaga suasana untuk mendukung kegiatan belajar.
        
        2. Menjaga nama baik pribadi, almamater, dan kerukunan antar penghuni Asrama.
        
        3. Penghuni Asrama diizinkan menempati setelah check-in asrama yang telah ditetapkan.
        
        4. Penghuni asrama wajib meninggalkan asrama pada akhir masa huniannya sesuai dengan tanggal yang telah ditetapkan.
        
        5. Penghuni dilarang membuat keributan atau gangguan (membunyikan radio, tape, alat musik dengan keras atau alat sejenis lainnya yang dapat mengganggu ketenteraman penghuni lainnya).
        
        6. Penghuni dilarang menjemur pakaian di depan kamar, di balkon, dan di tempat yang tidak diperuntukan untuk itu.
        
        7. Penghuni dilarang memindahkan atau mengeluarkan setiap fasilitas kamar milik asrama.
        
        8. Penghuni dilarang menempelkan dan mencoret-coret permukaan pintu masuk, dinding atau fasilitas kamar milik asrama.
        
        9. Penghuni diwajibkan mengunci kamar bila ingin meninggalkan kamar untuk menghindari masalah yang tidak diinginkan. Pengelola Asrama tidak bertanggung jawab terhadap kehilangan barang-barang milik penghuni asrama.
        
        10. Dilarang menyimpan, mengedarkan dan atau memanfaatkan barang cetakan, audio, visual yang bersifat pornografi, minuman keras, narkotika obat-obatan terlarang, senjata tajam dan senjata api, serta melakukan pencurian.
        
        11. Dilarang melakukan tindakan/kegiatan yang bersifat melanggar aturan Universitas Telkom atau hukum yang berlaku di Indonesia seperti berjudi, asusila, dan tindakan lainnya.
        
        12. Dilarang melakukan perbuatan/perlakuan tidak senonoh atau perbuatan yang melanggar kesusilaan, norma agama, diskriminasi, dan pelecehan seksual.
        
        13. Dilarang menyimpan/mengedarkan/menggunakan barang yang bersifat asusila, pornografi maupun pornoaksi dalam bentuk apapun dengan media apapun.
        
        14. Dilarang menyimpan atau menggunakan senjata api, senjata tajam, bahan peledak (bahan-bahan laboratorium yang berpotensi sebagai bahan peledak) atau benda-benda berbahaya lainnya.
        
        15. Dilarang membawa hewan peliharaan di lingkungan asrama.
        
        16. Dilarang merokok di dalam dan luar kawasan asrama.
        
        17. Dilarang memakai pakaian yang tidak sopan atau tidak sesuai dengan norma kesopanan yang berlaku di lingkungan asrama.
        
        18. Dilarang menyimpan barang di atas tempat tidur kosong.
        
        19. Dilarang meletakkan rak sepatu di lorong depan kamar.
        """.trimIndent(),
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