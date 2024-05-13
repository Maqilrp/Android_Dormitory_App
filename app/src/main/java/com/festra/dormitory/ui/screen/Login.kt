package com.festra.dormitory.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.festra.dormitory.R
import com.festra.dormitory.navigation.Screen
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

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
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.asrama), // Gambar asrama
                    contentDescription = "Login Image",
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Selamat Datang  Di\n" +
                            " Dormitory Application",
                    style = MaterialTheme.typography.bodyLarge,
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

    // State variables for displaying error messages
    var emailErrorText by remember { mutableStateOf("") }
    var passwordErrorText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        // Title above the login inputs
        Text(
            text = "LOGIN FESTRA",
            style = MaterialTheme.typography.bodyLarge,
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
                label = { Text("Email")},
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
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
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            )
            // Display error message if password field is empty
            Text(
                text = passwordErrorText,
                color = Color.Red,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

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

                // Firebase authentication logic
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email.value, password.value)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Sign in success, navigate to home screen
                            navController.navigate(Screen.Home.route)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("SIGN IN")
        }

        // Button to navigate to the registration screen
        Button(
            onClick = { navController.navigate(Screen.Register.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register")
        }
    }
}