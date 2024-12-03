package com.example.ui.navigation

internal sealed class Screen(
    val route: String,
) {
    data object Calendar : Screen(CALENDAR_SCREEN_ROUTE)

    companion object {
        private const val CALENDAR_SCREEN_ROUTE = "calendar_screen_route"
    }
}