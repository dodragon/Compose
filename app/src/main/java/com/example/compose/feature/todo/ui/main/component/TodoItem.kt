package com.example.compose.feature.todo.ui.main.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.compose.R
import com.example.compose.feature.todo.domain.model.Todo
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TodoItem(
    todo: Todo,
    onClick: (item: Todo) -> Unit = {},
    onDeleteClick: (item: Todo) -> Unit = {}
) {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    Row(

    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    onClick(todo)
                },
            painter = painterResource(id = R.drawable.baseline_delete_24),
            contentDescription = "delete",
            tint = Color(0xFFA51212)
        )

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = format.format(Date(todo.date)),
                color = if(todo.isDone) Color.Gray else Color.Black,
                style = TextStyle(textDecoration = if(todo.isDone) TextDecoration.LineThrough else TextDecoration.None)
            )

            Text(
                text = todo.title,
                color = if(todo.isDone) Color.Gray else Color.Black,
                style = TextStyle(textDecoration = if(todo.isDone) TextDecoration.LineThrough else TextDecoration.None)
            )

            if(todo.isDone) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_done_24),
                    contentDescription = "done",
                    tint = Color(0xFF00BCD4)
                )
            }
        }
    }
}

