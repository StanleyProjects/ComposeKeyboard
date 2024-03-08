package sp.ax.jc.keyboard

import androidx.annotation.DrawableRes
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class KeyboardStyle(
    val fontSize: TextUnit,
    val colors: KeyboardColors,
    val corners: Dp,
    @DrawableRes
    val backspaceIconId: Int,
)

val LocalKeyboardStyle = staticCompositionLocalOf {
    val textColor = Color.Black
    KeyboardStyle(
        fontSize = 15.sp,
        colors = KeyboardColors(
            text = textColor,
            pressed = textColor.copy(alpha = 0.25f),
        ),
        corners = 16.dp,
        backspaceIconId = android.R.drawable.ic_input_delete,
    )
}
