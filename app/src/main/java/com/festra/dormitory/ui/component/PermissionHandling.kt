package com.festra.dormitory.ui.component//
//import android.Manifest
//import android.content.pm.PackageManager
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.layout.Column
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.platform.LocalContext
//import androidx.core.content.ContextCompat
//import kotlinx.coroutines.launch
//
//@Composable
//fun PermissionHandlingScreen() {
//    val context = LocalContext.current
//    val coroutineScope = rememberCoroutineScope()
//
//    val requestPermissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission()
//    ) { isGranted: Boolean ->
//        if (isGranted) {
//            // Permission is granted, perform operations like reading external storage
//            coroutineScope.launch {
//                // Your code to read external storage goes here
//            }
//        } else {
//            // Permission is denied, handle accordingly (show a message, request again, etc.)
//        }
//    }
//
//    Column {
//        Text("This screen handles permissions")
//        Button(onClick = {
//            // Check if permission is already granted
//            if (ContextCompat.checkSelfPermission(
//                    context,
//                    Manifest.permission.READ_EXTERNAL_STORAGE
//                ) == PackageManager.PERMISSION_GRANTED
//            ) {
//                // Permission is already granted, perform operations like reading external storage
//                coroutineScope.launch {
//                    // Your code to read external storage goes here
//                }
//            } else {
//                // Permission is not granted, request it
//                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
//            }
//        }) {
//            Text("Request Permission")
//        }
//    }
//}
