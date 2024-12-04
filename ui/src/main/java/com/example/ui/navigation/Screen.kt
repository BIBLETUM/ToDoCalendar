package com.example.ui.navigation

internal sealed class Screen(
    val route: String,
) {
    data object Calendar : Screen(CALENDAR_SCREEN_ROUTE)

    data object TaskDetail : Screen(ROUTE_TASK_DETAIL) {
        private const val ROUTE_FOR_ARGS = "Task_detail_screen_route"
        const val KEY_TASK_ID = "task_id"

        fun getRouteWithArgs(taskId: Int): String {
            return ("$ROUTE_FOR_ARGS/${taskId}")
        }
    }

    companion object {
        private const val CALENDAR_SCREEN_ROUTE = "Calendar_screen_route"
        private const val ROUTE_TASK_DETAIL = "Task_detail_screen_route/{${TaskDetail.KEY_TASK_ID}}"
    }
}