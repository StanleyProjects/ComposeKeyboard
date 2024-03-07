package sp.ax.jc.keyboard

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class KeyboardStyle(
    val fontSize: TextUnit,
    val textColor: Color,
    val corners: Dp,
)

val LocalKeyboardStyle = staticCompositionLocalOf {
    val textColor = Color.Black
    KeyboardStyle(
        fontSize = 15.sp,
        textColor = textColor,
        corners = 16.dp,
    )
}
