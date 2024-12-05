package theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class MeetsColorScheme(
    val primaryStroke: Color,
    val primary: Color,
    val secondary: Color,
    val lightGray: Color,
    val gray: Color,
    val backgroundDisabled: Color,
    val buttonTextDisabled: Color,
    val placeholder: Color,
    val error: Color,
    val gradientViolet: List<Pair<Float, Color>>,
    val gradientSmoky: List<Pair<Float, Color>>,
    val green: Color,
)

val LightColorScheme = MeetsColorScheme(
    primaryStroke = Color(0xFF9A41FE),
    primary = Color(0xFF9A10F0),
    secondary = Color(0xFFF6F6FA),
    lightGray = Color(0xFFEFEFEF),
    gray = Color(0xFF76778E),
    backgroundDisabled = Color(0xFFF6F6FA),
    buttonTextDisabled = Color(0xFF9797AF),
    placeholder = Color(0xFFADB5BD),
    error = Color(0xFFF0114C),
    green = Color(0xFF00BF59),
    gradientViolet = listOf(
        0.0f to Color(0xFFED3CCA),
        0.15f to Color(0xFFDF34D2),
        0.29f to Color(0xFFD02BD9),
        0.43f to Color(0xFFBF22E1),
        0.57f to Color(0xFFAE1AE8),
        0.71f to Color(0xFF9A10F0),
        0.85f to Color(0xFF8306F7),
        1f to Color(0xFF6600FF)
    ),
    gradientSmoky = listOf(
        0.0f to Color(0xFFFEF1FB),
        0.15f to Color(0xFFFDF1FC),
        0.29f to Color(0xFFFCF0FC),
        0.43f to Color(0xFFFBF0FD),
        0.57f to Color(0xFFF9EFFD),
        0.71f to Color(0xFFF8EEFE),
        0.85f to Color(0xFFF6EEFE),
        1f to Color(0xFFF4EDFF)
    ),
)

val LocalColorScheme = staticCompositionLocalOf {
    LightColorScheme
}