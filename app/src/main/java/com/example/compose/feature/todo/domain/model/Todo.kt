package com.example.compose.feature.todo.domain.model

import android.icu.util.Calendar
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TODO")
data class Todo(
    val title: String,
    val date: Long = Calendar.getInstance().timeInMillis,
    val isDone: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}
