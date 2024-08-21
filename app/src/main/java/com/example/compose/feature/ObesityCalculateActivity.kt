package com.example.compose.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.base.BaseActivity
import com.example.compose.ui.theme.ComposeTheme

class ObesityCalculateActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }

    @Composable
    override fun ResultView() {
        TODO("Not yet implemented")
    }

    override fun onBack() {
        TODO("Not yet implemented")
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        ComposeTheme {
            ResultView()
        }
    }
}