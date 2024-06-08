package com.festra.dormitory.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.festra.dormitory.R
import com.festra.dormitory.navigation.Screen
import com.festra.dormitory.ui.theme.DormitoryAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                .clip(RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center

            ) {
                Image(
                    painter = painterResource(id = R.drawable.asrama), // Gambar asrama
                    contentDescription = "Login Image",
                    modifier = Modifier.fillMaxWidth()
                    .height(205.dp) // Set height as needed
                    .clip(RoundedCornerShape(8.dp)), // Apply rounded corners to the image itself
                contentScale = ContentScale.Crop
                )
                Text(
                    text = "Selamat Datang  Di\n" +
                            " Dormitory Application",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)
                )
            }
            LoginContent(Modifier.padding(padding), navController)
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginContent(modifier: Modifier, navController: NavController) {
    val context = LocalContext.current // Retrieve the context
    val keyboardController = LocalSoftwareKeyboardController.current

    // State variables for email and password fields
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // State variables for displaying error messages
    var emailErrorText by remember { mutableStateOf("") }
    var passwordErrorText by remember { mutableStateOf("") }

    // Loading dialog state
    var showDialog by rememberSaveable { mutableStateOf(false) }
    // Timeout state
    var registrationTimeout by rememberSaveable { mutableStateOf(false) }
    // Cancellation state
    var isCancelled by rememberSaveable { mutableStateOf(false) }


    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // Title above the login inputs
            Text(
                text = "LOGIN FESTRA",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(10.dp))
            Divider(Modifier.padding(vertical = 1.dp))
            Spacer(modifier = Modifier.height(30.dp))

            // Input field for email
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Email Address",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 4.dp)
                )
                TextField(
                    value = email.value,
                    onValueChange = {
                        email.value = it
                        emailErrorText = "" // Clear error message when input changes
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Email") },
                    leadingIcon = { Icon(imageVector = Icons.Filled.Email, contentDescription = "Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
                )
                // Display error message if email field is empty
                Text(
                    text = emailErrorText,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Input field for password
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Password",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 4.dp)
                )
                TextField(
                    value = password.value,
                    onValueChange = {
                        password.value = it
                        passwordErrorText = "" // Clear error message when input changes
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Password") },
                    leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = "Password Icon") },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (passwordVisible) "Hide Password" else "Show Password"
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done, autoCorrect = false),
                )
                // Display error message if password field is empty
                Text(
                    text = passwordErrorText,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Button to submit login
            Button(
                onClick = {
                    // Validate input fields
                    if (email.value.isEmpty()) {
                        emailErrorText = "Email cannot be empty"
                        return@Button
                    }
                    if (password.value.isEmpty()) {
                        passwordErrorText = "Password cannot be empty"
                        return@Button
                    }

                    // show dialog statement true
                    showDialog = true
                    isCancelled = false

                    // Firebase authentication logic
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email.value, password.value)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user = task.result?.user
                                user?.let {
                                    val db = FirebaseFirestore.getInstance()
                                    db.collection("users").document(it.uid).get()
                                        .addOnSuccessListener { document ->
                                            if (document != null && document.exists()) {
                                                val role = document.getString("role") ?: false
                                                if (role == "admin") {
                                                    navController.navigate(Screen.HomeAdmin.route)
                                                    Toast.makeText(context, "Login Admin Successful.", Toast.LENGTH_SHORT).show()
                                                } else {
                                                    navController.navigate(Screen.Home.route)
                                                    Toast.makeText(context, "Login Mahasiswa Successful.", Toast.LENGTH_SHORT).show()
                                                }
                                            } else {
                                                Toast.makeText(context, "User not found.", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                        .addOnFailureListener { exception ->
                                            Toast.makeText(context, "Failed to fetch user data.", Toast.LENGTH_SHORT).show()
                                        }
                                        .addOnCompleteListener {
                                            showDialog = false
                                        }
                                }
                            } else {
                                Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                                showDialog = false
                            }
                        }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("SIGN IN")
            }

            // Button to navigate to the registration screen
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Belum punya akun?")

                Spacer(modifier = Modifier.width(4.dp)) // Add some space between the texts

                Text(
                    text = "Register",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier.clickable { navController.navigate(Screen.Register.route) }
                )
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

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    DormitoryAppTheme {
        Login(rememberNavController())
    }
}