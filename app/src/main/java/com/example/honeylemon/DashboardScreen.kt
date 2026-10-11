package com.example.honeylemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen(
    assignments: List<Assignment>,
    onToggleComplete: (Int) -> Unit,
    onAddClick: () -> Unit
) {
    val pending = assignments.filter { !it.isCompleted }

    Scaffold(
        topBar = {
            AppBar(
                title = "Welcome back, ${SampleData.studentName}!",
                canNavigateBack = false,
                onBackClick = {}
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Add assignment")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Quick stats
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                StatCard("Active Courses", SampleData.courses.size.toString(), Modifier.weight(1f))
                StatCard("Pending Tasks", pending.size.toString(), Modifier.weight(1f))
            }

            // Active courses
            Text("Active Courses", style = MaterialTheme.typography.titleLarge)
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(SampleData.courses) { course ->
                    CourseInfo(
                        courseName = course.courseName,
                        courseCode = course.courseCode,
                        instructor = course.instructor,
                        progress = course.progress
                    )
                }
            }

            // Urgent deadlines (top 2 pending)
            Text("Urgent Deadlines", style = MaterialTheme.typography.titleLarge)
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                pending.take(2).forEach { a ->
                    AssignmentCard(
                        title = a.title,
                        course = a.course,
                        dueDate = a.dueDate,
                        priority = a.priority,
                        isCompleted = a.isCompleted,
                        onToggleComplete = { onToggleComplete(a.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun StatCard(label: String, value: String, modifier: Modifier = Modifier) {
    ElevatedCard(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(text = label, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
