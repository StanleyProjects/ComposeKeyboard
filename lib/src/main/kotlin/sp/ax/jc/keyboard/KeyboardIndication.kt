package sp.ax.jc.keyboard

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

internal class KeyboardIndication(
    private val pressed: Color,
    private val hovered: Color,
    private val corners: Dp,
) : Indication {
    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        val pressedState = interactionSource.collectIsPressedAsState()
        val hoveredState = interactionSource.collectIsHoveredAsState()
        val focusedState = interactionSource.collectIsFocusedAsState()
        return remember(interactionSource, pressed, hovered, corners) {
            KeyboardIndicationInstance(
                pressed = pressed,
                hovered = hovered,
                corners = corners,
                pressedState = pressedState,
                hoveredState = hoveredState,
                focusedState = focusedState,
            )
        }
    }
}
