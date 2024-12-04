package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.ui.navigation.AppNavGraph
import com.example.ui.navigation.Screen
import com.example.ui.screens.calendar.CalendarScreenRoot
import com.example.ui.screens.task_detail.TaskDetailScreenRoot

@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    AppNavGraph(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        navHostController = navHostController,
        calendarScreen = {
            CalendarScreenRoot(navigateToTaskDetail = { taskId ->
                navHostController.navigate(Screen.TaskDetail.getRouteWithArgs(taskId))
            })
        },
        taskDetailScreen = { TaskDetailScreenRoot() }
    )
}
