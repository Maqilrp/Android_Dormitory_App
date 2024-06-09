package com.festra.dormitory.ui.screen

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.festra.dormitory.R
import com.festra.dormitory.navigation.Screen
import com.festra.dormitory.ui.component.NoGedungKamarDropdown
import com.festra.dormitory.ui.theme.DormitoryAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.util.UUID

@Composable
fun RegisterScreen(navController: NavHostController) {
    Scaffold(
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
        ScreenContent(Modifier.padding(paddingValues), navController)
    }
}

@Composable
fun ScreenContent(modifier: Modifier, navController: NavHostController) {
    var namaLengkap by rememberSaveable { mutableStateOf("") }
    var namaLengkapError by rememberSaveable { mutableStateOf(false) }
    var email by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf(false) }
    // password variable
    var password by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    // foto
    var foto by rememberSaveable { mutableStateOf("") }
    var fotoError by rememberSaveable { mutableStateOf(false) }
    var selectedImageUri by rememberSaveable {
        mutableStateOf<Uri?>(null)
    }

    var nim by rememberSaveable { mutableStateOf("") }
    var nimError by rememberSaveable { mutableStateOf(false) }
    var noTelephone by rememberSaveable { mutableStateOf("") }
    var noTeleponError by rememberSaveable { mutableStateOf(false) }
    var noGedungKamar by rememberSaveable { mutableStateOf("") }
    var noGedungKamarError by rememberSaveable { mutableStateOf(false) }

    // firebase initialize
    val context = LocalContext.current
    val firestore = FirebaseFirestore.getInstance()
    val storage = FirebaseStorage.getInstance()

    // Loading dialog state
    var showDialog by rememberSaveable { mutableStateOf(false) }
    // Timeout state
    var registrationTimeout by rememberSaveable { mutableStateOf(false) }
    // Cancellation state
    var isCancelled by rememberSaveable { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    var registrationJob: Job by remember { mutableStateOf(Job()) }
    // Timeout configuration
    val timeoutMillis = 30000L // 30 seconds
    val timeoutHandler = CoroutineExceptionHandler { _, _ ->
        showDialog = false
        registrationJob.cancel()
        registrationTimeout = true
        Toast.makeText(context, "Registration timed out", Toast.LENGTH_SHORT).show()
    }

    // photo picker
    val pickMedia =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            if (uri != null) {
                selectedImageUri = uri
                foto = uri.lastPathSegment ?: ""
                Log.d("PhotoPicker", "Selected URI: $uri")
            } else {
                Log.d("PhotoPicker", "No Media Selected")
            }
        }

    // permission storage
//    val storagePermissionState = rememberPermissionState(Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION)
//    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
//        if (isGranted) {
//            // Permission is granted, perform operations like reading external storage
//            Toast.makeText(context, "Storage permission granted", Toast.LENGTH_SHORT).show()
//        } else {
//            // Permission is denied, handle accordingly (show a message, request again, etc.)
//            Toast.makeText(context, "Storage permission denied", Toast.LENGTH_SHORT).show()
//        }
//    }

//    var selectedImageUri by remember {
//        mutableStateOf<Uri?>(null)
//    }
//
//    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.PickVisualMedia(),
//        onResult = { uri -> selectedImageUri = uri }
//    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // namaLengkap textField
        OutlinedTextField(
            value = namaLengkap,
            onValueChange = { namaLengkap = it },
            label = { Text(text = stringResource(R.string.nama_lengkap)) },
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
        // email textField
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = stringResource(R.string.email)) },
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
        // password textField
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = stringResource(R.string.password)) },
            isError = passwordError,
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (passwordVisible) "Hide Password" else "Show Password"
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            supportingText = {
                ErrorHint(
                    errorType = ErrorType.InvalidPassword,
                    isVisible = passwordError
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        // foto textField
        if (selectedImageUri != null) {
            Image(
                painter = rememberAsyncImagePainter(selectedImageUri),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        }

        OutlinedTextField(
            value = foto,
            onValueChange = { foto = it },
            readOnly = true,
            label = { Text(text = stringResource(R.string.foto)) },
            isError = fotoError,
            trailingIcon = { IconPicker(fotoError) },
            supportingText = {
                ErrorHint(
                    errorType = ErrorType.InvalidFoto,
                    isVisible = fotoError
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            foto = ""
        }) {
            Text(text = "Pilih Foto")
        }

        // nim textField
        OutlinedTextField(
            value = nim,
            onValueChange = { nim = it },
            label = { Text(text = stringResource(R.string.nim)) },
            isError = nimError,
            trailingIcon = { IconPicker(nimError) },
            supportingText = { ErrorHint(errorType = ErrorType.InvalidNim, isVisible = nimError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )
        // noTelephone textField
        OutlinedTextField(
            value = noTelephone,
            onValueChange = { noTelephone = it },
            label = { Text(text = stringResource(R.string.nomor_telepon)) },
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
        // noGedungKamar textField
        NoGedungKamarDropdown(
            selectedText = noGedungKamar,
            onSelectedTextChange = { noGedungKamar = it })

        // confirmation button
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    namaLengkapError = (namaLengkap == "")
                    emailError = !email.endsWith("@gmail.com")
                    passwordError = password.length <= 7
                    fotoError = (foto == "")
                    nimError = nim.length != 10
                    noTeleponError = noTelephone.length <= 10
                    noGedungKamarError = (noGedungKamar == "")


                    if (namaLengkapError || emailError || passwordError || fotoError || nimError || noTeleponError || noGedungKamarError) return@Button

                    showDialog = true
                    isCancelled = false

                    registrationJob = scope.launch(timeoutHandler) {
                        try {
                            withTimeout(timeoutMillis) {
                                // Check if the email already exists in Firebase Authentication
                                FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            val signInMethods = task.result?.signInMethods
                                            if (!signInMethods.isNullOrEmpty()) {
                                                // Email already exists, show an error message to the user
                                                Toast.makeText(
                                                    context,
                                                    "Email already exists. Please use a different email.",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            } else {
                                                // Email doesn't exist, proceed with user registration
                                                val userId = UUID.randomUUID().toString()
                                                if (selectedImageUri != null) {
                                                    val storageRef =
                                                        storage.reference.child("images/$userId.jpg")
                                                    val uploadTask =
                                                        storageRef.putFile(selectedImageUri!!)

                                                    uploadTask.addOnSuccessListener {
                                                        storageRef.downloadUrl.addOnSuccessListener { uri ->
                                                            val imageUrl = uri.toString()
                                                            val userData = hashMapOf(
                                                                "name" to namaLengkap,
                                                                "email" to email,
                                                                "password" to password,
                                                                "foto" to imageUrl,
                                                                "nim" to nim,
                                                                "noTelephone" to noTelephone,
                                                                "noGedungKamar" to noGedungKamar,
                                                                "role" to "user"
                                                            )
                                                            // Create Firebase Authentication user with email and password
                                                            FirebaseAuth.getInstance()
                                                                .createUserWithEmailAndPassword(
                                                                    email,
                                                                    password
                                                                )
                                                                .addOnSuccessListener { authResult ->
                                                                    // Registration successful, now save user data in Firestore
                                                                    val user = authResult.user
                                                                    user?.let { firebaseUser ->
                                                                        firestore.collection("users")
                                                                            .document(firebaseUser.uid)
                                                                            .set(userData)
                                                                            .addOnSuccessListener {
                                                                                showDialog = false
                                                                                navController.navigate(
                                                                                   Screen.Login.route
                                                                                )
                                                                                Toast.makeText(
                                                                                    context,
                                                                                    "Registration successful",
                                                                                    Toast.LENGTH_SHORT
                                                                                ).show()
                                                                            }
                                                                            .addOnFailureListener { exception ->
                                                                                showDialog = false
                                                                                Toast.makeText(
                                                                                    context,
                                                                                    "Failed to save user data: ${exception.message}",
                                                                                    Toast.LENGTH_SHORT
                                                                                ).show()
                                                                            }
                                                                    }
                                                                }
                                                                .addOnFailureListener { exception ->
                                                                    showDialog = false
                                                                    Toast.makeText(
                                                                        context,
                                                                        "Registration failed: ${exception.message}",
                                                                        Toast.LENGTH_SHORT
                                                                    ).show()
                                                                }
                                                        }
                                                    }.addOnFailureListener {
                                                        showDialog = false
                                                        Toast.makeText(
                                                            context,
                                                            "Image upload failed",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                }
                                            }
                                        } else {
                                            // Error fetching sign-in methods
                                            Toast.makeText(
                                                context,
                                                "Failed to check email existence: ${task.exception?.message}",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }


                            }
                        } catch (e: TimeoutCancellationException) {
                            showDialog = false
                            registrationTimeout = true
                            Toast.makeText(context, "Registration timed out", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            ) {
                Text(text = "Register")
            }

            Button(onClick = {
                navController.popBackStack()
                isCancelled = true
            }) {
                Text(text = "Cancel")
            }
        }
    }


    // Show the loading dialog
    if (showDialog) {
        Dialog(
            onDismissRequest = {
                showDialog = false
                registrationTimeout = false
                isCancelled = true
            },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        Color.DarkGray,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                CircularProgressIndicator()
            }
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

enum class ErrorType {
    InvalidName,
    InvalidEmail,
    InvalidPassword,
    InvalidFoto,
    InvalidNim,
    InvalidNoTelephone,
    InvalidNoGedungKamar
}

@Composable
fun ErrorHint(errorType: ErrorType, isVisible: Boolean) {
    if (isVisible) {
        val errorMessage = when (errorType) {
            ErrorType.InvalidName -> "Invalid name"
            ErrorType.InvalidEmail -> "Invalid email format"
            ErrorType.InvalidPassword -> "Password must be at least 8 characters long"
            ErrorType.InvalidFoto -> "Invalid photo"
            ErrorType.InvalidNim -> "Invalid NIM"
            ErrorType.InvalidNoTelephone -> "Invalid phone number"
            ErrorType.InvalidNoGedungKamar -> "Invalid building and room number"
        }
        Text(text = errorMessage)
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    DormitoryAppTheme {
        RegisterScreen(rememberNavController())
    }
}