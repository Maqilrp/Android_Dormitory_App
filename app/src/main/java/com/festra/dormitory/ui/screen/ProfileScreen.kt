package com.festra.dormitory.ui.screen

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.festra.dormitory.ui.component.BottomBarNavigationComponent
import com.festra.dormitory.ui.component.ConfirmationDialog
import com.festra.dormitory.ui.component.NoGedungKamarDropdown
import com.festra.dormitory.ui.theme.DormitoryAppTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.skydoves.landscapist.glide.GlideImage

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProfileScreen(navController: NavController, userViewModel: UserViewModel = viewModel()) {
    val userUid by userViewModel.userUid.collectAsState()
    val context = LocalContext.current

    userUid?.let { uid ->
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var nim by remember { mutableStateOf("") }
        var noTelephone by remember { mutableStateOf("") }
        var noGedungKamar by remember { mutableStateOf("") }
        var foto by remember { mutableStateOf("") }
        var isLoading by remember { mutableStateOf(true) }

        LaunchedEffect(uid) {
            val db = FirebaseFirestore.getInstance()
            db.collection("users").document(uid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        name = document.getString("name") ?: ""
                        email = document.getString("email") ?: ""
                        nim = document.getString("nim") ?: ""
                        foto = document.getString("foto") ?: ""
                        noTelephone = document.getString("noTelephone") ?: ""
                        noGedungKamar = document.getString("noGedungKamar") ?: ""
                        isLoading = false
                    } else {
                        Toast.makeText(context, "User not found.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { _ ->
                    Toast.makeText(context, "Failed to fetch user data.", Toast.LENGTH_SHORT).show()
                }
        }

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Profile") },
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
                ProfileContent(
                    modifier = Modifier.padding(paddingValues),
                    name = name,
                    email = email,
                    nim = nim,
                    noTelephone = noTelephone,
                    noGedungKamar = noGedungKamar,
                    foto = foto,
                    onNameChange = { name = it },
                    onEmailChange = { email = it },
                    onNimChange = { nim = it },
                    onNoTelephoneChange = { noTelephone = it },
                    onNoGedungKamarChange = { noGedungKamar = it },
                    onSubmit = { newUser ->
                        val db = FirebaseFirestore.getInstance()
                        db.collection("users").document(uid).update(newUser)
                            .addOnSuccessListener {
                                Toast.makeText(context, "Profile updated.", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    context,
                                    "Failed to update profile.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    },
                    navController = navController
                )
            }
        }
    } ?: run {
        Toast.makeText(context, "UID is not set.", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun ProfileContent(
    modifier: Modifier,
    name: String,
    email: String,
    nim: String,
    noTelephone: String,
    noGedungKamar: String,
    foto: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onNimChange: (String) -> Unit,
    onNoTelephoneChange: (String) -> Unit,
    onNoGedungKamarChange: (String) -> Unit,
    onSubmit: (Map<String, Any>) -> Unit,
    navController: NavController
) {
    // show dialog
    var showDialog by remember {
        mutableStateOf(false)
    }
    // nama
    var namaLengkapError by rememberSaveable { mutableStateOf(false) }
    // email
    var emailError by rememberSaveable { mutableStateOf(false) }
    //nim
    var nimError by rememberSaveable { mutableStateOf(false) }
    // no telp
    var noTeleponError by rememberSaveable { mutableStateOf(false) }
    //no gedung kamar
    var noGedungKamarError by rememberSaveable { mutableStateOf(false) }

    // foto
    var fotoBaru by rememberSaveable { mutableStateOf("") }
    var selectedImageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    val pickMedia =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            if (uri != null) {
                selectedImageUri = uri
                fotoBaru = uri.toString()
                Log.d("PhotoPicker", "Selected URI: $uri")
            } else {
                Log.d("PhotoPicker", "No Media Selected")
            }
        }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .clickable { pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) },
            contentAlignment = Alignment.Center
        ) {
            if (foto.isEmpty()) {
                Text(text = "Foto Mahasiswa/i", color = Color.DarkGray)
            } else {
                GlideImage(
                    imageModel = selectedImageUri ?: foto,
                    contentDescription = "Foto Mahasiswa/i",
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .size(100.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // name textfield
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Nama Lengkap") },
                isError = namaLengkapError,
                trailingIcon = { IconPicker(namaLengkapError) },
                supportingText = {
                    ErrorHint(
                        errorType = ErrorType.InvalidName,
                        isVisible = namaLengkapError
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            //email textfield
            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                label = { Text("Email Address") },
                isError = emailError,
                trailingIcon = { IconPicker(emailError) },
                supportingText = {
                    ErrorHint(
                        errorType = ErrorType.InvalidEmail,
                        isVisible = emailError
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            // nim
            OutlinedTextField(
                value = nim,
                onValueChange = onNimChange,
                label = { Text("NIM") },
                isError = nimError,
                trailingIcon = { IconPicker(nimError) },
                supportingText = {
                    ErrorHint(
                        errorType = ErrorType.InvalidNim,
                        isVisible = nimError
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            OutlinedTextField(
                value = noTelephone,
                onValueChange = onNoTelephoneChange,
                label = { Text("Nomor Telepon") },
                isError = noTeleponError,
                trailingIcon = { IconPicker(noTeleponError) },
                supportingText = {
                    ErrorHint(
                        errorType = ErrorType.InvalidNoTelephone,
                        isVisible = noTeleponError
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            NoGedungKamarDropdown(
                selectedText = noGedungKamar,
                onSelectedTextChange = onNoGedungKamarChange
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {

                        namaLengkapError = (name == "")
                        emailError = !email.endsWith("@gmail.com")
                        nimError = nim.length != 10
                        noTeleponError = noTelephone.length <= 10
                        noGedungKamarError = (noGedungKamar == "")


                        if (namaLengkapError || emailError || nimError || noTeleponError || noGedungKamarError) return@Button
                        showDialog = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Submit")
                }
            }
        }
    }
    if (showDialog){
        ConfirmationDialog(
            onDismissRequest = { showDialog = false }
        ) {
            val newUser = mapOf(
                "name" to name,
                "email" to email,
                "nim" to nim,
                "noTelephone" to noTelephone,
                "noGedungKamar" to noGedungKamar,
                "foto" to (fotoBaru.ifEmpty { foto })
            )
            onSubmit(newUser)
            showDialog = false
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    DormitoryAppTheme {
        ProfileScreen(rememberNavController())
    }
}
