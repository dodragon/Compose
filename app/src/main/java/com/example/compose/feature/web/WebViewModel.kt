package com.example.compose.feature.web

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class WebViewModel : ViewModel() {

    val url = mutableStateOf("https://google.com")

    private val _undoSharedFlow = MutableSharedFlow<Boolean>()
    val undoSharedFlow = _undoSharedFlow.asSharedFlow()

    private val _redoSharedFlow = MutableSharedFlow<Boolean>()
    val redoSharedFlow = _redoSharedFlow.asSharedFlow()

    fun undo() {
        viewModelScope.launch {
            _undoSharedFlow.emit(true)
        }
    }

    fun redo() {
        viewModelScope.launch {
            _redoSharedFlow.emit(true)
        }
    }
}