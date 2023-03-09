package com.memksim.todo.ui.pages.main.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.memksim.todo.R
import com.memksim.todo.ui.pages.main.MainPageItemUiState
import java.util.*

@ExperimentalMaterialApi
@Composable
fun MainList(
    tasks: List<MainPageItemUiState>,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        items(
            items = tasks
        ) { task: MainPageItemUiState ->
            TaskItem(item = task)
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun TaskItem(item: MainPageItemUiState) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White,
        shape = RectangleShape,
        elevation = 4.dp,
        onClick = {

        }
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Checkbox(
                checked = false,
                onCheckedChange = {

                }
            )
            Column {
                Text(
                    text = item.title,
                    fontSize = 24.sp
                )
                if (!item.note.isNullOrBlank()) {
                    Text(
                        text = item.note,
                        color = Color.Gray,
                        fontSize = 20.sp
                    )
                }
                if (item.date.isNullOrEmpty().not() && item.time.isNullOrEmpty().not()) {
                    Row {
                        Text(
                            text = String.format(
                                Locale.getDefault(),
                                stringResource(R.string.date_and_time),
                                item.date,
                                item.time
                            ),
                            color = Color.Gray,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}

