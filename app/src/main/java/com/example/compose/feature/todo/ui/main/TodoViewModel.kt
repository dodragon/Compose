package com.example.compose.feature.todo.ui.main

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.feature.todo.domain.model.Todo
import com.example.compose.feature.todo.domain.repository.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(
    application: Application,
    private val todoRepo: TodoRepository
) : AndroidViewModel(application) {

    private val _items = mutableStateOf(emptyList<Todo>())
    val items: State<List<Todo>> = _items

    private var recentlyDeleteTodo: Todo? = null

    fun addTodo(
        text: String
    ) {
        viewModelScope.launch {
            todoRepo.insert(Todo(title = text))
        }
    }

    fun toggle(
        uid: Int
    ) {
        val todo = _items.value.find { it.uid == uid }
        todo?.let {
            viewModelScope.launch {
                todoRepo.update(it.copy(isDone = !it.isDone).apply { this.uid = it.uid })
            }
        }
    }

    fun delete(
        uid: Int
    ) {
        val todo = _items.value.find { it.uid == uid }
        todo?.let {
            viewModelScope.launch {
                todoRepo.delete(it)
                recentlyDeleteTodo = it
            }
        }
    }

    fun restoreTodo() {
        viewModelScope.launch {
            todoRepo.insert(recentlyDeleteTodo ?: return@launch)
            recentlyDeleteTodo = null
        }
    }
}