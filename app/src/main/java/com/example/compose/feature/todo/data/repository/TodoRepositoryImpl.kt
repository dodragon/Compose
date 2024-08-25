package com.example.compose.feature.todo.data.repository

import android.app.Application
import androidx.room.Room
import com.example.compose.feature.todo.data.data_source.TodoDatabase
import com.example.compose.feature.todo.domain.model.Todo
import com.example.compose.feature.todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    application: Application
) : TodoRepository {

    private val db = Room.databaseBuilder(
        application,
        TodoDatabase::class.java,
        "todo-db"
    ).build()

    override fun observeTodos(): Flow<List<Todo>> {
        return db.todoDao().todos()
    }

    override suspend fun insert(entity: Todo) {
        db.todoDao().insert(entity)
    }

    override suspend fun update(entity: Todo) {
        db.todoDao().update(entity)
    }

    override suspend fun delete(entity: Todo) {
        db.todoDao().delete(entity)
    }
}