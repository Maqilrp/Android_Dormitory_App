package com.festra.dormitory.ui.screen.history

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.festra.dormitory.ui.component.BottomBarNavigationComponent
import com.festra.dormitory.ui.component.TopAppBarNavigationComponent
import com.festra.dormitory.ui.screen.UserViewModel
import com.festra.dormitory.ui.theme.DormitoryAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

data class PerizinanData(
    val name: String,
    val alasan: String,
    val status: String
)

@Composable
fun HistoryPerizinanScreen(navController: NavController, userViewModel: UserViewModel = viewModel()) {
    //get user role
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
            Toast.makeText(context,"gagal mengambil role", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBarNavigationComponent(navController = navController, judul = "History Perizinan", currentUserRole = currentUserRole)
        },
        bottomBar = {
            BottomBarNavigationComponent(navController = navController, selectedIconIndex = 1, userViewModel = userViewModel )
        }
    ) { paddingValues ->
        HistoryPerizinanContent(modifier = Modifier.padding(paddingValues), navController, userViewModel)
    }
}

@Composable
fun HistoryPerizinanContent(modifier: Modifier, navController: NavController, userViewModel: UserViewModel) {
    val context = LocalContext.current
    val currentUser = FirebaseAuth.getInstance().currentUser
    var perizinanList by remember { mutableStateOf<List<PerizinanData>>(emptyList()) }

    LaunchedEffect(currentUser) {
        currentUser?.let { user ->
            val db = FirebaseFirestore.getInstance()
            db.collection("perizinan")
                .whereEqualTo("uid", user.uid)
                .get()
                .addOnSuccessListener { documents ->
                    val list = documents.map { document ->
                        PerizinanData(
                            name = document.getString("name") ?: "",
                            alasan = document.getString("alasan") ?: "",
                            status = document.getString("status") ?: "Pending"
                        )
                    }
                    perizinanList = list
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Failed to fetch history data.", Toast.LENGTH_SHORT).show()
                }
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (perizinanList.isEmpty()) {
            Text(text = "No permission history found.")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(perizinanList) { perizinan ->
                    CardItem(
                        name = perizinan.name,
                        alasan = perizinan.alasan,
                        status = perizinan.status
                    )
                }
            }
        }
    }
}
@Composable
fun CardItem(
    name: String,
    alasan: String,
    status: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White) // Set background color to white
                .padding(16.dp)
        ) {
            Text(text = "Name: $name", style = MaterialTheme.typography.titleMedium, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Alasan: $alasan", style = MaterialTheme.typography.bodyMedium, color = Color.Gray, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Status: $status", style = MaterialTheme.typography.bodySmall, color = if (status == "Approved") Color.Green else Color.Red)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HistoryPerizinanPreview() {
    DormitoryAppTheme {
        HistoryPerizinanScreen(rememberNavController())
    }
}
