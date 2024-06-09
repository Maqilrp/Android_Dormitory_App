package com.festra.dormitory.ui.screen.admin

import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.festra.dormitory.R
import com.festra.dormitory.ui.component.BottomBarAdminNavigationComponent
import com.festra.dormitory.ui.screen.mahasiswa.BottomNavigationItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

data class PerizinanData(
    val id: String = "",
    val name: String = "",
    val status: String = "Pending",
    val phoneNumber: String = "",
    val gedung: String = "",
    val alasan: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Admin_Perizinan(navController: NavController) {
    val items = listOf(
        BottomNavigationItem(
            title = "history",
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart
        ),
        BottomNavigationItem(
            title = "home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            title = "profile",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle
        ),
    )

    var selectedIconIndex by rememberSaveable { mutableStateOf(2) }
    var showPerizinanContent by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Admin") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            BottomBarAdminNavigationComponent(navController = navController, selectedIconIndex = 0)
        }
    ) { paddingValues ->
        AdminPerizinanContent(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            showPerizinanContent = showPerizinanContent,
            onPerizinanClick = { showPerizinanContent = !showPerizinanContent }
        )
    }
}

@Composable
fun AdminPerizinanContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    showPerizinanContent: Boolean,
    onPerizinanClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hi Admin!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = "Welcome back to your panel.",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "PERIZINAN",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        AdminMenuPerizinanButton(
            label = "Perizinan",
            icon = Icons.Default.Assignment,
            onClick = onPerizinanClick
        )

        if (showPerizinanContent) {
            Spacer(modifier = Modifier.height(16.dp))
            PerizinanContent()
        }
    }
}

@Composable
fun AdminMenuPerizinanButton(label: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun PerizinanContent() {
    val perizinanList = remember { mutableStateListOf<PerizinanData>() }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("perizinan")
            .get()
            .addOnSuccessListener { documents ->
                val list = documents.map { document ->
                    document.toObject<PerizinanData>().copy(id = document.id)
                }
                perizinanList.addAll(list)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to fetch perizinan data.", Toast.LENGTH_SHORT).show()
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        perizinanList.forEach { perizinan ->
            PerizinanCard(perizinan)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun PerizinanCard(perizinan: PerizinanData) {
    var expanded by remember { mutableStateOf(false) }
    var selectedStatus by remember { mutableStateOf(perizinan.status) }

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.paket),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.Gray, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = perizinan.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = perizinan.phoneNumber,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Box {
                    Text(
                        text = selectedStatus,
                        color = when (selectedStatus) {
                            "Approved" -> Color.Green
                            "Ditolak" -> Color.Red
                            else -> Color.Gray
                        },
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { expanded = true }
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Menunggu") },
                            onClick = {
                                selectedStatus = "Menunggu"
                                updatePerizinanStatus(perizinan.id, "Menunggu")
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Approved") },
                            onClick = {
                                selectedStatus = "Approved"
                                updatePerizinanStatus(perizinan.id, "Approved")
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Ditolak") },
                            onClick = {
                                selectedStatus = "Ditolak"
                                updatePerizinanStatus(perizinan.id, "Ditolak")
                                expanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                Text("Status: ${perizinan.status}", fontSize = 14.sp, fontWeight = FontWeight.Light)
                Text("Alasan: ${perizinan.alasan}", fontSize = 14.sp, fontWeight = FontWeight.Light)
            }
        }
    }
}

fun updatePerizinanStatus(id: String, newStatus: String) {
    val db = FirebaseFirestore.getInstance()
    db.collection("perizinan").document(id)
        .update("status", newStatus)
        .addOnSuccessListener {
            // Status updated successfully
        }
        .addOnFailureListener {
            // Handle error
        }
}
