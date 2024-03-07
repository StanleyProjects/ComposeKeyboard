package sp.sample.keyboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalView
import sp.ax.jc.keyboard.Keyboard

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
                    Keyboard(
                        modifier = Modifier.fillMaxWidth(),
                        enabled = true,
                        onClick = { char ->
                            showToast("Char: \"$char\"")
                        },
                    )
                }
            }
        }
    }
}
