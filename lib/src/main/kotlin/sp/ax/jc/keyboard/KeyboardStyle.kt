package sp.ax.jc.keyboard

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class KeyboardStyle(
    val textColor: Color,
)

val LocalKeyboardStyle = staticCompositionLocalOf {
    KeyboardStyle(
        textColor = Color.Black,
    )
}
