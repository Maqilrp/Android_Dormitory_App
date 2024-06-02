package com.festra.dormitory.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.festra.dormitory.ui.theme.DormitoryAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, title: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceBright,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        content = { paddingValues ->
            DetailContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            )
        }
    )
}

@Composable
fun DetailContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row {
                    Text(text = "Header 1", modifier = Modifier.weight(1f))
                    Text(text = "", modifier = Modifier.weight(1f))
                    Text(text = "", modifier = Modifier.weight(1f))
                }
                Row {
                    Text(text = "Info:", modifier = Modifier.weight(1f))
                    Text(text = "Info", modifier = Modifier.weight(1f))
                    Text(text = "", modifier = Modifier.weight(1f))
                }
                Row {
                    Text(text = "Info:", modifier = Modifier.weight(1f))
                    Text(text = "Info", modifier = Modifier.weight(1f))
                    Text(text = "", modifier = Modifier.weight(1f))
                }
                Row {
                    Text(text = "Status:", modifier = Modifier.weight(1f))
                    Text(text = "Info", modifier = Modifier.weight(1f))
                    Text(text = "", modifier = Modifier.weight(1f))
                }
                Row {
                    Spacer(modifier = Modifier.weight(2f))
                    Button(
                        onClick = { /* TODO */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Detail")
                    }
                }
            }
        }
    }
}

