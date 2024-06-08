package com.festra.dormitory.ui.screen.mahasiswa

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.festra.dormitory.navigation.Screen
import com.festra.dormitory.ui.component.BottomBarNavigationComponent
import com.festra.dormitory.ui.theme.DormitoryAppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PaketScreen(navController: NavController) {
    // data store
//    val dataStore = SettingsDataStore(LocalContext.current)
//    val showList by dataStore.layoutFlow.collectAsState(true)


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Paket") },
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
        PaketItem(modifier = Modifier.padding(paddingValues), navController)
    }
}

@Composable
fun PaketItem(modifier: Modifier = Modifier, navController: NavController) {
    var namaLengkap by remember { mutableStateOf(TextFieldValue("")) }
    var nim by remember { mutableStateOf(TextFieldValue("")) }
    var nomorGedungKamar by remember { mutableStateOf(TextFieldValue("")) }
    var buktiGambar by remember { mutableStateOf(TextFieldValue("")) }
    var nomorTelepon by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input fields
        OutlinedTextField(
            value = namaLengkap,
            onValueChange = { namaLengkap = it },
            leadingIcon = { Icon(imageVector = Icons.Filled.PermIdentity, contentDescription = "nama") },
            label = { Text("Nama Lengkap") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = nim,
            onValueChange = { nim = it },
            leadingIcon = { Icon(imageVector = Icons.Filled.AddCard, contentDescription = "Nim") },
            label = { Text("NIM") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = nomorGedungKamar,
            onValueChange = { nomorGedungKamar = it },
            leadingIcon = { Icon(imageVector = Icons.Filled.Apartment, contentDescription = "nim") },
            label = { Text("Nomor Gedung & Kamar") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = buktiGambar,
            onValueChange = { buktiGambar = it },
            leadingIcon = { Icon(imageVector = Icons.Filled.CameraAlt, contentDescription = "nim") },
            label = { Text("Bukti Gambar") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = nomorTelepon,
            onValueChange = { nomorTelepon = it },
            leadingIcon = { Icon(imageVector = Icons.Filled.Phone, contentDescription = "nim") },
            label = { Text("Nomor Telepon") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Submit and Cancel buttons
        Row {
            Button(
                onClick = { /* Handle submit */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
            ) {
                Text("Submit")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { navController.navigate(Screen.Home.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Cancel")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaketPreview() {
    DormitoryAppTheme {
        PaketScreen(rememberNavController())
    }
}