package com.festra.dormitory.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.festra.dormitory.R
import com.festra.dormitory.ui.theme.DormitoryAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController) {
    Scaffold (
        topBar = {
            Column(
                modifier = Modifier.padding()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.asrama_780x470_jpeg),
                        contentDescription = stringResource(R.string.gambar_asrama),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Selamat Datang  Di\nDormitory Application",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    ) { paddingValues ->
        ScreenContent(Modifier.padding(paddingValues))
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {
    var namaLengkap by rememberSaveable { mutableStateOf("") }
    var namaLengkapError by rememberSaveable { mutableStateOf(false) }
    var email by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf(false) }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf(false) }
    var foto by rememberSaveable { mutableStateOf("") }
    var fotoError by rememberSaveable { mutableStateOf(false) }
    var nim by rememberSaveable { mutableStateOf("") }
    var nimError by rememberSaveable { mutableStateOf(false) }
    var noTelepon by rememberSaveable { mutableStateOf("") }
    var noTeleponError by rememberSaveable { mutableStateOf(false) }
    var noGedungKamar by rememberSaveable { mutableStateOf("") }
    var noGedungKamarError by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = namaLengkap,
            onValueChange = { namaLengkap = it },
            label = { Text(text = stringResource(R.string.nama_lengkap)) },
            isError = namaLengkapError,
            trailingIcon = { IconPicker(namaLengkapError) },
            supportingText = { ErrorHint(namaLengkapError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = stringResource(R.string.email)) },
            isError = emailError,
            trailingIcon = { IconPicker(emailError) },
            supportingText = { ErrorHint(emailError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = stringResource(R.string.password)) },
            isError = passwordError,
            trailingIcon = { IconPicker(passwordError) },
            supportingText = { ErrorHint(passwordError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = foto,
            onValueChange = { foto = it },
            label = { Text(text = stringResource(R.string.foto)) },
            isError = fotoError,
            trailingIcon = { IconPicker(fotoError) },
            supportingText = { ErrorHint(fotoError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = nim,
            onValueChange = { nim = it },
            label = { Text(text = stringResource(R.string.nim)) },
            isError = nimError,
            trailingIcon = { IconPicker(nimError) },
            supportingText = { ErrorHint(nimError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = noTelepon,
            onValueChange = { noTelepon = it },
            label = { Text(text = stringResource(R.string.nomor_telepon)) },
            isError = noTeleponError,
            trailingIcon = { IconPicker(noTeleponError) },
            supportingText = { ErrorHint(noTeleponError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = noGedungKamar,
            onValueChange = { noGedungKamar = it },
            label = { Text(text = stringResource(R.string.nomor_gedung_dan_kamar)) },
            isError = noGedungKamarError,
            trailingIcon = { IconPicker(noGedungKamarError) },
            supportingText = { ErrorHint(noGedungKamarError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )
        Button(
            onClick = {
                namaLengkapError = (namaLengkap == "")
                emailError = (email == "")
                passwordError = (password == "")
                fotoError = (foto == "")
                nimError = (nim == "")
                noTeleponError = (noTelepon == "")
                noGedungKamarError = (noGedungKamar == "")
                if (namaLengkapError || emailError || passwordError || fotoError || nimError || noTeleponError || noGedungKamarError) return@Button
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(R.string.submit))
        }
        Button(
            onClick = { /* Perform cancel action */ },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(R.string.cancel))
        }
    }
}

@Composable
fun IconPicker(isError: Boolean) {
    if (isError) {
        Icon(
            imageVector = Icons.Filled.Warning,
            contentDescription = null
        )
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.input_invalid))
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    DormitoryAppTheme {
        RegisterScreen(rememberNavController())
    }
}