package com.example.honeylemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentOrganizerApp()
        }
    }
}

@Composable
fun StudentOrganizerApp() {
    var darkMode by remember { mutableStateOf(false) }

    // Shared, hoisted state: all screens read/modify the same list
    val assignments = remember {
        mutableStateListOf<Assignment>().apply { addAll(SampleData.assignments) }
    }

    val toggleComplete: (Int) -> Unit = { id ->
        val index = assignments.indexOfFirst { it.id == id }
        if (index >= 0) {
            assignments[index] = assignments[index].copy(isCompleted = !assignments[index].isCompleted)
        }
    }

    MaterialTheme(colorScheme = if (darkMode) darkColorScheme() else lightColorScheme()) {
        val navController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        Scaffold(
            bottomBar = {
                // Hide the bottom bar on the Add Assignment screen
                if (currentRoute != Screen.AddAssignment.route) {
                    BottomBar(navController = navController, currentRoute = currentRoute)
                }
            },
            // Each screen has its own Scaffold + AppBar that handles the status bar inset
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Dashboard.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Dashboard.route) {
                    DashboardScreen(
                        assignments = assignments,
                        onToggleComplete = toggleComplete,
                        onAddClick = { navController.navigate(Screen.AddAssignment.route) }
                    )
                }
                composable(Screen.Assignments.route) {
                    AssignmentsScreen(
                        assignments = assignments,
                        onToggleComplete = toggleComplete,
                        onAddClick = { navController.navigate(Screen.AddAssignment.route) }
                    )
                }
                composable(Screen.Profile.route) {
                    ProfileScreen()
                }
                composable(Screen.Settings.route) {
                    SettingsScreen(
                        darkMode = darkMode,
                        onDarkModeChange = { darkMode = it }
                    )
                }
                composable(Screen.AddAssignment.route) {
                    AddAssignmentScreen(
                        onBack = { navController.popBackStack() },
                        onSave = { newAssignment ->
                            val newId = (assignments.maxOfOrNull { it.id } ?: 0) + 1
                            assignments.add(0, newAssignment.copy(id = newId))
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}