package com.example.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
internal fun AppNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    calendarScreen: @Composable () -> Unit,
    taskDetailScreen: @Composable () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Screen.Calendar.route
    ) {
        composable(Screen.Calendar.route) {
            calendarScreen()
        }
        composable(Screen.TaskDetail.route) {
            taskDetailScreen()
        }
    }
}