package com.example.compose.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.R
import com.example.compose.base.BaseActivity
import com.example.compose.ui.theme.ComposeTheme
import java.util.Timer
import kotlin.concurrent.timer

class StopWatchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResultView()
        }
    }

    @Composable
    override fun ResultView() {
        val viewModel = viewModel<StopWatchViewModel>()

        val sec = viewModel.sec.value
        val milliSecond = viewModel.milliSecond.value
        val isRunning = viewModel.isRunning.value
        val lapTimes = viewModel.lapTimes.value

        MainScreen(
            sec = sec,
            milliSecond = milliSecond,
            isRunning = isRunning,
            lapTimes = lapTimes,
            onReset = { viewModel.reset() },
            onToggle = { running ->
                if(running) viewModel.pause() else viewModel.start()
            },
            onRecordLapTime = { viewModel.recordLapTime() }
        )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    sec: Int,
    milliSecond: Int,
    isRunning: Boolean,
    lapTimes: List<String>,
    onReset: () -> Unit,
    onToggle: (Boolean) -> Unit,
    onRecordLapTime: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "스톱워치") })
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(96.dp))

            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "$sec", fontSize = 100.sp)
                Text(text = "$milliSecond")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center
            ) {
                lapTimes.forEach { lapTime ->
                    Text(text = lapTime)
                }
            }

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FloatingActionButton(
                    onClick = {
                        onReset()
                    },
                    containerColor = Color.Red,
                    contentColor = Color.White
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_refresh_24),
                        contentDescription = "reset"
                    )
                }

                FloatingActionButton(
                    onClick = {
                        onToggle(isRunning)
                    },
                    containerColor = Color.Green,
                    contentColor = Color.White
                ) {
                    Image(
                        painter = painterResource(id = if (isRunning) R.drawable.ic_baseline_pause_24 else R.drawable.ic_baseline_play_arrow_24),
                        contentDescription = "start/pause"
                    )
                }

                Button(onClick = {
                    onRecordLapTime()
                }) {
                    Text(text = "랩 타임")
                }
            }
        }

        it
    }
}

class StopWatchViewModel : ViewModel() {

    private var time = 0

    private var timerTask: Timer? = null

    private val _sec = mutableIntStateOf(0)
    val sec: State<Int> = _sec

    private val _milliSecond = mutableIntStateOf(0)
    val milliSecond: State<Int> = _milliSecond

    private val _isRunning = mutableStateOf(false)
    val isRunning: State<Boolean> = _isRunning

    private val _lapTimes = mutableStateOf(mutableListOf<String>())
    val lapTimes: State<List<String>> = _lapTimes

    private var lap = 1


    fun start() {
        _isRunning.value = true
        timerTask = timer(period = 10L) {
            time++
            _sec.value = time / 100
            _milliSecond.value = time % 100
        }
    }

    fun pause() {
        _isRunning.value = false
        timerTask?.cancel()
    }

    fun reset() {
        timerTask?.cancel()
        time = 0
        _sec.value = 0
        _milliSecond.value = 0
        _isRunning.value = false
        _lapTimes.value.clear()
        lap = 1
    }

    fun recordLapTime() {
        _lapTimes.value.add(0, "$lap LAP : ${sec.value}.${milliSecond.value}")
        lap++
    }
}