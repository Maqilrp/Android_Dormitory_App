package com.festra.dormitory.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.festra.dormitory.R
import com.festra.dormitory.ui.theme.DormitoryAppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AboutScreen(navController: NavController) {
    // data store
//    val dataStore = SettingsDataStore(LocalContext.current)
//    val showList by dataStore.layoutFlow.collectAsState(true)

    val items = listOf(
        BottomNavigationItem(
            title = "History",
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart
        ),
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            title = "Profile",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle
        ),
    )

    var selectedIconIndex by rememberSaveable {
        mutableIntStateOf(1)
    }

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
                title = { Text(text = "About") },
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
//                            navController.navigate(item.title)
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
        AboutContent(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun AboutContent(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = "Simplify your\n" +
                    "dorm management \n" +
                    "with Festra"
        )

        Image(
            painter = painterResource(id = R.drawable.logo5050_1),
            contentDescription = "Festra"
        )

        Text(
            text = ".\n" +
                    "\n" +
                    "Festra dapat menyediakan layanan berupa platform atau website yang memudahkan para penghuni asrama untuk mengakses informasi seputar asrama, termasuk mengelola fasilitas asrama dan berkomunikasi dengan pengelola asrama.\n" +
                    "\n" +
                    "Platform yang disediakan oleh Festra dapat berisi informasi terkait jadwal kegiatan asrama, informasi terkait fasilitas asrama, serta informasi penting lainnya seperti pengumuman atau aturan-aturan yang berlaku di dalam asrama. Dengan platform ini, para penghuni asrama dapat dengan mudah mengakses informasi tersebut kapan saja dan di mana saja tanpa perlu bertemu langsung dengan pengelola asrama.\n" +
                    "\n" +
                    "Selain itu, platform yang disediakan oleh Festra juga dapat dilengkapi dengan fitur-fitur lain yang dapat memudahkan para penghuni asrama dalam mengelola kebutuhan mereka di dalam asrama. Misalnya, fitur reservasi atau pemesanan fasilitas asrama seperti ruangan rapat, lapangan olahraga, atau fasilitas lainnya yang dapat digunakan oleh para penghuni asrama. Dengan adanya fitur tersebut, penghuni asrama dapat dengan mudah mengelola dan menggunakan fasilitas asrama tanpa perlu menghubungi pengelola asrama secara langsung.\n" +
                    "\n" +
                    "Selain itu, platform yang disediakan oleh Festra juga dapat dilengkapi dengan fitur komunikasi antara para penghuni asrama dan pengelola asrama. Fitur ini memungkinkan para penghuni asrama untuk mengajukan pertanyaan, keluhan, atau permintaan bantuan kepada pengelola asrama secara online. Hal ini dapat memudahkan penghuni asrama dalam berkomunikasi dengan pengelola asrama, terutama jika mereka tidak bisa bertemu langsung atau ketika pengelola asrama sibuk.\n" +
                    "\n" +
                    "Dengan menyediakan platform atau website yang memudahkan para penghuni asrama dalam mengakses informasi dan mengelola fasilitas asrama, Festra dapat membantu penghuni asrama untuk lebih efektif dan efisien dalam mengelola kebutuhan mereka di dalam asrama. Sehingga, Festra memiliki potensi untuk menjadi salah satu penyedia layanan asrama yang terpercaya dan terdepan.",
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AboutPreview() {
    DormitoryAppTheme {
        AboutScreen(rememberNavController())
    }
}