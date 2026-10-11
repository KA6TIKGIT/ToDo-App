package com.example.honeylemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            AppBar(title = "Student Profile", canNavigateBack = false, onBackClick = {})
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StudentProfile(
                studentName = SampleData.studentName,
                studentId = SampleData.studentId,
                major = SampleData.major,
                // Placeholder icon -- swap for your own drawable, e.g. R.drawable.avatar
                avatarRes = android.R.drawable.ic_menu_myplaces
            )

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Academic Stats", style = MaterialTheme.typography.titleMedium)
                    StatRow("GPA", "3.82")
                    StatRow("Completed Credits", "84")
                    StatRow("Current Semester", "Fall 2026")
                }
            }

            Button(onClick = { /* TODO: edit profile */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Edit Profile")
            }
            OutlinedButton(onClick = { /* TODO: export summary */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Export Academic Summary")
            }
        }
    }
}

@Composable
private fun StatRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label)
        Text(value, fontWeight = FontWeight.Bold)
    }
}
