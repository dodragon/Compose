package com.example.compose.feature.todo.domain.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.compose.feature.todo.data.repository.TodoRepositoryImpl
import com.example.compose.feature.todo.domain.repository.TodoRepository
import com.example.compose.feature.todo.ui.main.TodoViewModel
import kotlin.reflect.KClass

class TodoViewModelFactory(
    private val application: Application,
    private val todoRepository: TodoRepository = TodoRepositoryImpl(application)
) : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(
                application = application,
                todoRepo = todoRepository
            ) as T
        }
        return super.create(modelClass)
    }
}