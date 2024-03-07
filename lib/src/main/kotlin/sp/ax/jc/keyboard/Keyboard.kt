package sp.ax.jc.keyboard

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
private fun KeyboardRow(
    height: Dp,
    enabled: Boolean,
    chars: CharArray,
    onClick: (Char) -> Unit,
    textStyle: TextStyle,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
    ) {
        for (char in chars) {
            val onClickState = rememberUpdatedState(onClick)
            BasicText(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = {
                                if (enabled) onClickState.value(char.uppercaseChar())
                            },
                            onTap = {
                                if (enabled) onClickState.value(char)
                            },
                        )
                    }
                    .wrapContentHeight(),
                text = char.toString(),
                style = textStyle,
            )
        }
    }
}

@Composable
fun Keyboard(
    modifier: Modifier = Modifier,
    textColor: Color = LocalKeyboardStyle.current.textColor,
    enabled: Boolean = true,
    onClick: (Char) -> Unit,
) {
    val rows = listOf(
        charArrayOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'),
        charArrayOf('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'),
        charArrayOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'),
        charArrayOf('z', 'x', 'c', 'v', 'b', 'n', 'm'),
    )
    val textStyle = TextStyle(
        color = textColor,
    )
    Column(modifier = modifier) {
        for (chars in rows) {
            KeyboardRow(
                height = 32.dp,
                enabled = enabled,
                chars = chars,
                onClick = onClick,
                textStyle = textStyle,
            )
        }
    }
}
