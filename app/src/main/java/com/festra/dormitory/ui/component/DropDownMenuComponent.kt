package com.festra.dormitory.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.PhoneInTalk
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.festra.dormitory.R

//@Composable
//fun DropdownMenuField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    label: @Composable () -> Unit,
//    items: List<String>,
//    isError: Boolean,
//    modifier: Modifier = Modifier,
//    shape: RoundedCornerShape = RoundedCornerShape(4.dp)
//) {
//    var expanded by remember { mutableStateOf(false) }
//    Box(
//        modifier = modifier
//            .clickable { expanded = true }
//            .background(MaterialTheme.colorScheme.surface, shape)
//            .padding(16.dp)
//    ) {
//        Text(text = value.ifEmpty { "Select an option" }, color = MaterialTheme.colorScheme.onSurface)
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            items.forEach { item ->
//                DropdownMenuItem(onClick = {
//                    onValueChange(item)
//                    expanded = false
//                }) {
//                    Text(text = item)
//                }
//            }
//        }
//    }
//}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ExposedDropdownMenuBox(
//    selectedText: String,
//    onSelectedTextChange: (String) -> Unit
//) {
//    val context = LocalContext.current
//    val gedungDanKamar = arrayOf("Gedung 1 - Kamar 1", "Gedung 2 - Kamar 2", "Gedung 3 - Kamar 3", "Gedung 4 - Kamar 4", "Gedung 5 - Kamar 5")
//    var expanded by remember { mutableStateOf(false) }
////    var selectedText by remember { mutableStateOf(gedungDanKamar[0]) }
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(32.dp)
//    ) {
//        ExposedDropdownMenuBox(
//            expanded = expanded,
//            onExpandedChange = {
//                expanded = !expanded
//            }
//        ) {
//            TextField(
//                value = selectedText,
//                onValueChange = {},
//                readOnly = true,
//                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//                modifier = Modifier.menuAnchor()
//            )
//
//            ExposedDropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false }
//            ) {
//                gedungDanKamar.forEach { item ->
//                    DropdownMenuItem(
//                        text = { Text(text = item) },
//                        onClick = {
//                            onSelectedTextChange(item)
//                            expanded = false
//                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
//                        }
//                    )
//                }
//            }
//        }
//    }
//}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoGedungKamarDropdown(
    selectedText: String,
    onSelectedTextChange: (String) -> Unit
) {
    val context = LocalContext.current
    val gedungDanKamar = arrayOf(
        "Gedung 1 - Kamar 1", "Gedung 2 - Kamar 2", "Gedung 3 - Kamar 3",
        "Gedung 4 - Kamar 4", "Gedung 5 - Kamar 5"
    )
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                leadingIcon = { Icon(imageVector = Icons.Filled.Apartment, contentDescription = "Telepon") },
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                label = { Text(text = stringResource(R.string.nomor_gedung_dan_kamar)) }
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                gedungDanKamar.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            onSelectedTextChange(item)
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

