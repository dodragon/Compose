package com.example.compose.exam

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.base.BaseActivity
import com.example.compose.ui.theme.ComposeTheme

class LazyColumnActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResultView()
        }
    }

    @Composable
    private fun ColumnList() {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier.background(Color.Green)
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            for(i in 1..50) {
                Text(text = "HI $i")
            }
        }
    }

    @Composable
    private fun LazyColumnList() {
        LazyColumn(
            modifier = Modifier.background(Color.Green)
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item {
                Text(text = "Header")
            }
            items(50) { index ->
                Text(text = "HI $index")
            }
        }
    }

    @Composable
    override fun ResultView() {
        //ColumnList()
        LazyColumnList()
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