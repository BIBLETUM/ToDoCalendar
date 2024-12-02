package theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun ToDoCalendarTheme(
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalColorScheme provides LightColorScheme,
    ) {
        MaterialTheme(
            content = content
        )
    }
}

object ToDoCalendarTheme {
    val colors: MeetsColorScheme
        @Composable get() = LocalColorScheme.current
}