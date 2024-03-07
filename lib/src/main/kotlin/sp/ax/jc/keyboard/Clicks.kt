package sp.ax.jc.keyboard

import androidx.compose.foundation.Indication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.debugInspectorInfo

internal fun Modifier.clicks(
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource,
    indication: Indication,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
): Modifier {
    return composed(
        inspectorInfo = debugInspectorInfo {
            name = "clicks"
            properties["enabled"] = enabled
            properties["onClick"] = onClick
            properties["onLongClick"] = onLongClick
            properties["indication"] = indication
            properties["interactionSource"] = interactionSource
        },
        factory = {
            val onClickState = rememberUpdatedState(onClick)
            val onLongClickState = rememberUpdatedState(onLongClick)
            val lastPressState = getLastPressState(
                enabled = enabled,
                interactionSource = interactionSource,
            )
            Modifier
                .indication(interactionSource = interactionSource, indication = indication)
                .pointerInput(interactionSource, enabled) {
                    detectTapGestures(
                        onPress = { offset ->
                            if (enabled) {
                                onPress(
                                    offset = offset,
                                    lastPressState = lastPressState,
                                    interactionSource = interactionSource,
                                )
                            }
                        },
                        onLongPress = {
                            if (enabled) onLongClickState.value()
                        },
                        onTap = {
                            if (enabled) onClickState.value()
                        },
                    )
                }
        },
    )
}
