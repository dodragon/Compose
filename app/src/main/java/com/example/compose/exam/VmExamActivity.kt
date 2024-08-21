package com.example.compose.exam

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.compose.base.BaseActivity
import com.example.compose.ui.theme.ComposeTheme

class VmExamActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResultView()
        }
    }

    @Composable
    override fun ResultView() {
        val viewModel = viewModels<ExamViewModel>().value

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = viewModel.data.value,
                fontSize = 30.sp
            )
            Button(onClick = {
                viewModel.changeValue("World")
            }) {
                Text(text = "변경")
            }
        }
    }

    override fun onBack() {
        finish()
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        ComposeTheme {
            ResultView()
        }
    }
}

class ExamViewModel : ViewModel() {
    private val _data = mutableStateOf("Hello")
    val data: State<String> = _data

    fun changeValue(
        value: String
    ) {
        _data.value = value
    }
}