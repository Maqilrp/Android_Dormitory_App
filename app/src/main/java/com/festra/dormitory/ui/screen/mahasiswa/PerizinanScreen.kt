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
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.TypeSpecimen
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
fun PerizinanScreen(navController: NavController) {
    // data store
//    val dataStore = SettingsDataStore(LocalContext.current)
//    val showList by dataStore.layoutFlow.collectAsState(true)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Perizinan") },
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
        PerizinanContent(modifier = Modifier.padding(paddingValues), navController)
    }
}

@Composable
fun PerizinanContent(modifier: Modifier = Modifier, navController: NavController) {
    var nim by remember { mutableStateOf(TextFieldValue("")) }
    var jenisPerizinan by remember { mutableStateOf(TextFieldValue("")) }
    var nomorGedungKamar by remember { mutableStateOf(TextFieldValue("")) }
    var tanggalWaktu by remember { mutableStateOf(TextFieldValue("")) }
    var alasan by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input fields
        OutlinedTextField(
            value = nim,
            onValueChange = { nim = it },
            leadingIcon = { Icon(imageVector = Icons.Filled.PermIdentity, contentDescription = "nim") },
            label = { Text("NIM") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = nomorGedungKamar,
            onValueChange = { nomorGedungKamar = it },
            leadingIcon = { Icon(imageVector = Icons.Filled.Apartment, contentDescription = "Gedung") },
            label = { Text("Nomor Gedung & Kamar") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = jenisPerizinan,
            onValueChange = { jenisPerizinan = it },
            leadingIcon = { Icon(imageVector = Icons.Filled.TypeSpecimen, contentDescription = "Jenis") },
            label = { Text("Jenis Perizinan") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = tanggalWaktu,
            onValueChange = { tanggalWaktu = it },
            leadingIcon = { Icon(imageVector = Icons.Filled.Timer, contentDescription = "Waktu") },
            label = { Text("Tanggal dan Waktu") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = alasan,
            onValueChange = { alasan = it },
            leadingIcon = { Icon(imageVector = Icons.Filled.Book, contentDescription = "Alasan") },
            label = { Text("Alasan") }
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
fun PerizinanPreview() {
    DormitoryAppTheme {
        PerizinanScreen(rememberNavController())
    }
}