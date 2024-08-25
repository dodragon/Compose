package com.example.compose.feature.todo.domain.repository

import com.example.compose.feature.todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun observeTodos(): Flow<List<Todo>>
    suspend fun insert(entity: Todo)
    suspend fun update(entity: Todo)
    suspend fun delete(entity: Todo)
}