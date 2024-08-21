package com.example.compose.exam

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.base.BaseActivity
import com.example.compose.ui.theme.ComposeTheme

class NavigationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResultView()
        }
    }

    @Composable
    override fun ResultView() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "first"
        ) {
            composable("first") {
                FirstScreen(navController)
            }
            composable("second") {
                SecondScreen(navController)
            }
            composable("third/{value}") { backStackEntry ->
                ThirdScreen(
                    navController = navController,
                    value = backStackEntry.arguments?.getString("value") ?: ""
                )
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

@Composable
fun FirstScreen(
    navController: NavHostController
) {
    val (value, setValue) = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "첫 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("second")
        }) {
            Text("두 번째")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = value, onValueChange = setValue)
        Button(onClick = {
            if(value.isNotEmpty()) {
                navController.navigate("third/$value")
            }
        }) {
            Text("세 번째")
        }
    }
}

@Composable
fun SecondScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "두 번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("뒤로 가기")
        }
    }
}

@Composable
fun ThirdScreen(
    navController: NavHostController,
    value: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "세 번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Text(value)
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("뒤로 가기")
        }
    }
}