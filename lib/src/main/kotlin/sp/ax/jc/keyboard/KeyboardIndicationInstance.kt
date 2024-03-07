package sp.ax.jc.keyboard

import androidx.compose.foundation.IndicationInstance
import androidx.compose.runtime.State
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.unit.Dp

internal class KeyboardIndicationInstance(
    private val pressed: Color,
    private val hovered: Color,
    private val corners: Dp,
    private val pressedState: State<Boolean>,
    private val hoveredState: State<Boolean>,
    private val focusedState: State<Boolean>,
) : IndicationInstance {
    override fun ContentDrawScope.drawIndication() {
        if (pressedState.value) {
            drawRoundRect(color = pressed, size = size, cornerRadius = CornerRadius(corners.toPx()))
        } else if (hoveredState.value || focusedState.value) {
            drawRoundRect(color = hovered, size = size, cornerRadius = CornerRadius(corners.toPx()))
        }
        drawContent()
    }
}
