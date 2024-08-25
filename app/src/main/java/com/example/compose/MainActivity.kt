package com.example.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.base.BaseActivity
import com.example.compose.feature.GalleryActivity
import com.example.compose.feature.GpsActivity
import com.example.compose.feature.HorizontalMeasureActivity
import com.example.compose.feature.ObesityCalculateActivity
import com.example.compose.feature.StopWatchActivity
import com.example.compose.feature.todo.ui.TodoActivity
import com.example.compose.feature.XylophoneActivity
import com.example.compose.feature.web.WebActivity
import com.example.compose.ui.theme.ComposeTheme

class MainActivity : BaseActivity() {

    private var backPressedTime: Long = 0

    private val itemList = listOf(
        "비만도 계산기",
        "스톱워치",
        "나만의 웹 브라우저",
        "전자액자",
        "수평 측정기",
        "실로폰",
        "GPS Map",
        "Todo List"
    )

    private val activityList = listOf(
        ObesityCalculateActivity::class.java,
        StopWatchActivity::class.java,
        WebActivity::class.java,
        GalleryActivity::class.java,
        HorizontalMeasureActivity::class.java,
        XylophoneActivity::class.java,
        GpsActivity::class.java,
        TodoActivity::class.java
    )

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
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(itemList.size) { i ->
                Button(
                    onClick = {
                        if(i < activityList.size) {
                            startActivity(Intent(this@MainActivity, activityList[i]))
                        }
                    }
                ) {
                    Text(
                        text = itemList[i],
                        fontSize = 18.sp
                    )
                }
            }
        }
    }

    override fun onBack() {
        finishAffinity()
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        ComposeTheme {
            ResultView()
        }
    }
}