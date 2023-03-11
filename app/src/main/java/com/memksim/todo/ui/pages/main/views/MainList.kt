package com.memksim.todo.ui.pages.main.views

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.memksim.todo.R
import com.memksim.todo.base.consts.COMPLETE_TASK_DELAY
import com.memksim.todo.base.consts.TAG
import com.memksim.todo.ui.pages.main.MainPageItemUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

@ExperimentalMaterialApi
@Composable
fun MainList(
    tasks: MutableState<List<MainPageItemUiState>>,
    paddingValues: PaddingValues,
    onCompleteTask: (MainPageItemUiState) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        items(
            items = tasks.value
        ) { task: MainPageItemUiState ->
            TaskItem(item = task){
                onCompleteTask(it)
            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun TaskItem(
    item: MainPageItemUiState,
    onCompleteTask: (MainPageItemUiState) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val isChecked = remember {
        mutableStateOf(item.isCompleted)
    }
    Surface(
        modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
        color = Color.Transparent,
        shape = RectangleShape,
        onClick = {

        }
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Checkbox(
                checked = isChecked.value,
                onCheckedChange = {
                    isChecked.value = it
                    coroutineScope.launch {
                        delay(COMPLETE_TASK_DELAY)
                        if(isChecked.value) onCompleteTask(item)
                    }
                }
            )
            Column {
                Text(
                    text = item.title,
                    fontSize = 24.sp
                )
                if (item.note.isBlank().not()) {
                    Text(
                        text = item.note,
                        color = Color.Gray,
                        fontSize = 20.sp
                    )
                }
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

