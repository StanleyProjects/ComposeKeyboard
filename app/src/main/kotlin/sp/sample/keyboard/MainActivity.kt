package sp.sample.keyboard

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sp.ax.jc.keyboard.Keyboard
import sp.ax.jc.keyboard.KeyboardColors

internal class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ComposeView(this)
        setContentView(view)
        view.setContent {
            val insets = LocalView
                .current
                .rootWindowInsets
                .toPaddings()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(insets),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    )
                    val textState = remember { mutableStateOf("") }
                    BasicText(text = textState.value)
                    Keyboard(
                        modifier = Modifier
                            .fillMaxWidth(),
                        enabled = true,
                        onClick = { char ->
                            textState.value += char
                        },
                        onBackspace = { isLongClick ->
                            val value = textState.value
                            if (isLongClick) {
                                if (value.isNotEmpty()) {
                                    textState.value = ""
                                }
                            } else {
                                if (value.isNotEmpty()) {
                                    textState.value = value.substring(0, value.lastIndex)
                                }
                            }
                        },
                    )
                    Keyboard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black),
                        fontSize = 12.sp,
                        colors = KeyboardColors(
                            text = Color.White,
                            pressed = Color.DarkGray,
                        ),
                        corners = 2.dp,
                        enabled = true,
                        onClick = { char ->
                            showToast("Char: \"$char\"")
                        },
                        onBackspace = {
                            // noop
                        },
                        backspacePainter = painterResource(id = R.drawable.ic_delete),
                    )
                }
            }
        }
    }
}
