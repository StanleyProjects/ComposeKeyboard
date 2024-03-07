package sp.ax.jc.keyboard

import androidx.compose.foundation.Indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
private fun KeyboardSpaceRow(
    indication: Indication,
    height: Dp,
    enabled: Boolean,
    onClick: () -> Unit,
    textStyle: TextStyle,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
    ) {
        Spacer(modifier = Modifier.weight(1f))
        val interactionSource = remember { MutableInteractionSource() }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(3f)
                .clicks(
                    enabled = enabled,
                    interactionSource = interactionSource,
                    indication = indication,
                    onClick = {
                        onClick()
                    },
                    onLongClick = {
                        // noop
                    },
                ),
        ) {
            BasicText(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "space",
                style = textStyle,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun KeyboardRow(
    indication: Indication,
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
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        for (char in chars) {
            val interactionSource = remember { MutableInteractionSource() }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(height)
                        .align(Alignment.Center)
                        .clicks(
                            enabled = enabled,
                            interactionSource = interactionSource,
                            indication = indication,
                            onClick = {
                                onClick(char)
                            },
                            onLongClick = {
                                onClick(char.uppercaseChar())
                            },
                        ),
                ) {
                    BasicText(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = char.toString(),
                        style = textStyle,
                    )
                }
            }
        }
    }
}

@Composable
fun Keyboard(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = LocalKeyboardStyle.current.fontSize,
    colors: KeyboardColors = LocalKeyboardStyle.current.colors,
    corners: Dp = LocalKeyboardStyle.current.corners,
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
        color = colors.text,
        fontSize = fontSize,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace,
    )
    val indication = remember(corners) {
        KeyboardIndication(
            pressed = colors.pressed,
            hovered = colors.pressed,
            corners = corners,
        )
    }
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            for (chars in rows) {
                KeyboardRow(
                    indication = indication,
                    height = 42.dp,
                    enabled = enabled,
                    chars = chars,
                    onClick = onClick,
                    textStyle = textStyle,
                )
            }
            KeyboardSpaceRow(
                indication = indication,
                height = 42.dp,
                enabled = enabled,
                onClick = {
                    onClick(' ')
                },
                textStyle = textStyle,
            )
        }
    }
}
