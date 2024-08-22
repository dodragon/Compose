package com.example.compose.feature

import android.app.Application
import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.compose.base.BaseActivity

class HorizontalMeasureActivity : BaseActivity() {

    private val viewModel by viewModels<MeasureViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        //화면 꺼지지 않게 하기
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        //화면 가로 고정
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        super.onCreate(savedInstanceState)

        lifecycle.addObserver(viewModel)

        setContent {
            ResultView()
        }
    }

    @Composable
    override fun ResultView() {
        TiltScreen(
            x = viewModel.x.value,
            y = viewModel.y.value
        )
    }

    override fun onBack() {
        finish()
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        ResultView()
    }
}

class MeasureViewModel(
    application: Application
) : AndroidViewModel(application), LifecycleEventObserver, SensorEventListener {

    private val _x = mutableFloatStateOf(0f)
    val x: State<Float> = _x
    private val _y = mutableFloatStateOf(0f)
    val y: State<Float> = _y

    private val sensorManager by lazy {
        application.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    private fun registerSensor() {
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    private fun unRegisterSensor() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            _x.value = event.values[0]
            _y.value = event.values[1]
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onStateChanged(
        source: LifecycleOwner,
        event: Lifecycle.Event
    ) {
        if(event == Lifecycle.Event.ON_RESUME) {
            registerSensor()
        }else if(event == Lifecycle.Event.ON_PAUSE) {
            unRegisterSensor()
        }
    }

}

@Composable
fun TiltScreen(
    x: Float,
    y: Float
) {
    val yCoord = x * 20
    val xCoord = y * 20

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val centerX = size.width / 2
        val centerY = size.height / 2

        drawCircle(
            color = Color.Green,
            radius = 100f,
            center = Offset(xCoord + centerX, yCoord + centerY)
        )

        drawCircle(
            color = Color.Black,
            radius = 100f,
            center = Offset(centerX, centerY),
            style = Stroke()
        )

        drawLine(
            color = Color.Black,
            start = Offset(centerX - 20, centerY),
            end = Offset(centerX + 20, centerY)
        )
        drawLine(
            color = Color.Black,
            start = Offset(centerX, centerY - 20),
            end = Offset(centerX, centerY + 20)
        )
    }
}