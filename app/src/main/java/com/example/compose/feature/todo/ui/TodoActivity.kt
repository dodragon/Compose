package com.example.compose.feature.todo.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.base.BaseActivity
import com.example.compose.feature.todo.domain.util.TodoViewModelFactory
import com.example.compose.feature.todo.ui.main.MainScreen
import com.example.compose.feature.todo.ui.main.TodoViewModel

class TodoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResultView()
        }
    }

    @Composable
    override fun ResultView() {
        val viewModel: TodoViewModel = viewModel(
            factory = TodoViewModelFactory(application)
        )

        MainScreen(viewModel)
    }

    override fun onBack() {
        finish()
    }
}