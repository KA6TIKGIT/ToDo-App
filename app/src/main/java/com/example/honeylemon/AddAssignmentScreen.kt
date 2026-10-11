package com.example.honeylemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddAssignmentScreen(
    onBack: () -> Unit,
    onSave: (Assignment) -> Unit
) {
    // Form state lives HERE (the screen), not inside AssignmentForm
    var title by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("Medium") }
    var description by remember { mutableStateOf("") }
    var reminderEnabled by remember { mutableStateOf(false) }

    val canSave = title.isNotBlank() && course.isNotBlank() && dueDate.isNotBlank()

    Scaffold(
        topBar = {
            AppBar(title = "Add New Assignment", canNavigateBack = true, onBackClick = onBack)
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                AssignmentForm(
                    title = title,
                    course = course,
                    dueDate = dueDate,
                    priority = priority,
                    description = description,
                    reminderEnabled = reminderEnabled,
                    onTitleChange = { title = it },
                    onCourseChange = { course = it },
                    onDueDateChange = { dueDate = it },
                    onPriorityChange = { priority = it },
                    onDescriptionChange = { description = it },
                    onReminderChange = { reminderEnabled = it }
                )
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(onClick = onBack, modifier = Modifier.weight(1f)) {
                        Text("Cancel")
                    }
                    Button(
                        onClick = {
                            onSave(
                                Assignment(
                                    id = 0, // real id is assigned by the caller
                                    title = title.trim(),
                                    course = course,
                                    dueDate = dueDate.trim(),
                                    priority = priority,
                                    description = description.trim(),
                                    reminderEnabled = reminderEnabled
                                )
                            )
                        },
                        enabled = canSave,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Save Assignment")
                    }
                }
            }
        }
    }
}
