package com.example.compose

import android.content.Intent
import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.base.BaseActivity
import com.example.compose.exam.LazyColumnActivity
import com.example.compose.exam.NavigationActivity
import com.example.compose.exam.TextFieldActivity
import com.example.compose.exam.ViewsActivity
import com.example.compose.exam.VmExamActivity
import com.example.compose.ui.theme.ComposeTheme

class MainActivity : BaseActivity() {

    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, VmExamActivity::class.java))

        setContent {
            ComposeTheme {
                Surface(
                    //modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    ResultView()
                }
            }
        }
    }

    @Composable
    override fun ResultView() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Blue)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Hello What")
            Text(text = "Hello Who")
        }
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