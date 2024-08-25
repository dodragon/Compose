package com.example.compose.feature.todo.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.compose.feature.todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TodoDao {

    @Query("SELECT * FROM todo ORDER BY date DESC")
    abstract fun todos(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: Todo)

    @Update
    abstract suspend fun update(entity: Todo)

    @Delete
    abstract suspend fun delete(entity: Todo)
}