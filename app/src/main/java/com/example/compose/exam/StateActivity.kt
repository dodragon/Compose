package com.example.compose.exam

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.base.BaseActivity
import com.example.compose.ui.theme.ComposeTheme

class StateActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResultView()
        }
    }

    @Composable
    override fun ResultView() {
        HomeScreen()
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

@Composable
fun HomeScreen(
    viewModel: StateViewModel = viewModel()
) {
    Column {
        Text(text = "Hello World")
        Button(onClick = {
            viewModel.changeValue("와아아아아")
        }) {
            Text(text = "Click")
        }
    }
}

class StateViewModel : ViewModel() {
    private val _value = mutableStateOf("Hello World")
    val value: State<String> = _value

    fun changeValue(
        value: String
    ) {
        _value.value = value
    }
}