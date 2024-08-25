package com.example.compose.feature.todo.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.compose.feature.todo.domain.model.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}