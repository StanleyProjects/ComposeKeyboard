package sp.ax.jc.keyboard

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class KeyboardStyle(
    val textColor: Color,
    val indication: KeyboardIndication,
)

val LocalKeyboardStyle = staticCompositionLocalOf {
    val textColor = Color.Black
    KeyboardStyle(
        textColor = textColor,
        indication = KeyboardIndication(
            pressed = textColor.copy(alpha = 0.25f),
            hovered = textColor.copy(alpha = 0.25f),
            corners = 16.dp,
        ),
    )
}
