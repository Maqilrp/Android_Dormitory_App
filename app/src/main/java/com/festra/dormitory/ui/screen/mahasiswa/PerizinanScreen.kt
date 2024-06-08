package com.festra.dormitory.ui.screen.mahasiswa

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Timer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.festra.dormitory.navigation.Screen
import com.festra.dormitory.ui.component.BottomBarNavigationComponent
import com.festra.dormitory.ui.component.jenisPerizinanDropdown
import com.festra.dormitory.ui.screen.UserViewModel
import com.festra.dormitory.ui.theme.DormitoryAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PerizinanScreen(navController: NavController, userViewModel: UserViewModel = viewModel()) {
    // Get the user role
    var currentUserRole by remember { mutableStateOf<String?>(null) }
    val currentUser = FirebaseAuth.getInstance().currentUser
    val context = LocalContext.current

    currentUser?.getIdToken(true)?.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val idTokenResult = task.result
            val role = idTokenResult?.claims?.get("role") as? String
            // Now 'role' contains the user's role
            currentUserRole = role
        } else {
            // Handle error
            Toast.makeText(context, "Failed to get role", Toast.LENGTH_SHORT).show()
        }
    }

    // Get user UID
    val userUid by userViewModel.userUid.collectAsState()

    userUid?.let { uid ->
        var name by remember { mutableStateOf("") }
        var nim by remember { mutableStateOf("") }
        var noGedungKamar by remember { mutableStateOf("") }
        var isLoading by remember { mutableStateOf(true) }

        LaunchedEffect(uid) {
            val db = FirebaseFirestore.getInstance()
            db.collection("users").document(uid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        name = document.getString("name") ?: ""
                        nim = document.getString("nim") ?: ""
                        noGedungKamar = document.getString("noGedungKamar") ?: ""
                        isLoading = false
                    } else {
                        Toast.makeText(context, "User not found.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Failed to fetch user data.", Toast.LENGTH_SHORT).show()
                }
        }

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Perizinan") },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    )
                )
            },
            bottomBar = {
                BottomBarNavigationComponent(
                    navController = navController,
                    selectedIconIndex = 2,
                    userViewModel = userViewModel
                )
            }
        ) { paddingValues ->
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Loading...")
                }
            } else {
                PerizinanContent(
                    modifier = Modifier.padding(paddingValues),
                    name = name,
                    nim = nim,
                    noGedungKamar = noGedungKamar,
                    uid = uid,
                    navController = navController
                )
            }
        }
    } ?: run {
        Toast.makeText(context, "UID is not set.", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun PerizinanContent(
    modifier: Modifier = Modifier,
    name: String,
    nim: String,
    noGedungKamar: String,
    uid: String,
    navController: NavController
) {
    var jenisPerizinan by remember { mutableStateOf("") }
    var tanggalWaktu by remember { mutableStateOf(TextFieldValue("")) }
    var alasan by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display user information
        // Read-only fields for user information
        OutlinedTextField(
            value = name,
            onValueChange = {},
            label = { Text("Nama") },
            readOnly = true,
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = nim,
            onValueChange = {},
            leadingIcon = { Icon(imageVector = Icons.Filled.PermIdentity, contentDescription = "nim") },
            label = { Text("NIM") },
            readOnly = true,
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = noGedungKamar,
            onValueChange = {},
            label = { Text("Nomor Gedung & Kamar") },
            readOnly = true,
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(imageVector = Icons.Filled.Apartment, contentDescription = "Gedung") },
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Input fields
        jenisPerizinanDropdown(
            selectedText = jenisPerizinan,
            onSelectedTextChange = { jenisPerizinan = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Tanggal dan waktu
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val calendar = Calendar.getInstance()

        OutlinedTextField(
            value = tanggalWaktu.text,
            onValueChange = {},
            label = { Text("Tanggal dan Waktu") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(imageVector = Icons.Filled.Timer, contentDescription = "Waktu") }
        )

        Spacer(modifier = Modifier.height(8.dp))


        Button(
            onClick = {
                val datePickerDialog = DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        calendar.set(year, month, dayOfMonth)
                        tanggalWaktu = TextFieldValue(dateFormat.format(calendar.time))
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.datePicker.minDate = System.currentTimeMillis()
                datePickerDialog.show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Pilih Tanggal")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = alasan,
            onValueChange = { alasan = it },
            label = { Text("Alasan") },
            maxLines = 3,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            leadingIcon = { Icon(imageVector = Icons.Filled.Book, contentDescription = "Alasan") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Submit and Cancel buttons
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = {
                    val izinData = hashMapOf(
                        "uid" to uid,
                        "name" to name,
                        "nim" to nim,
                        "noGedungKamar" to noGedungKamar,
                        "jenisPerizinan" to jenisPerizinan,
                        "tanggalWaktu" to tanggalWaktu.text,
                        "alasan" to alasan.text,
                        "status" to "menunggu"
                    )
                    val db = FirebaseFirestore.getInstance()
                    db.collection("perizinan").add(izinData)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Perizinan submitted.", Toast.LENGTH_SHORT).show()
                            navController.navigate(Screen.Home.route)
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Failed to submit perizinan.", Toast.LENGTH_SHORT).show()
                        }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                modifier = Modifier.weight(1f)
            ) {
                Text("Submit")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { navController.navigate(Screen.Home.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.weight(1f)
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