package com.example.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.base.BaseActivity
import com.example.compose.ui.theme.ComposeTheme

class MainActivity : BaseActivity() {

    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    ResultView()
                }
            }
        }
    }

    @Composable
    override fun ResultView() {
        Text(
            text = "Hello What",
            modifier = Modifier
        )
    }

    override fun onBack() {
        if(System.currentTimeMillis() - backPressedTime < 2000) {
            backPressedTime = System.currentTimeMillis()
            Toast.makeText(this, getString(R.string.main_back_toast), Toast.LENGTH_SHORT).show()
            return
        }

        if(System.currentTimeMillis() <= backPressedTime + 2000) {
            finish()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        ComposeTheme {
            ResultView()
        }
    }
}