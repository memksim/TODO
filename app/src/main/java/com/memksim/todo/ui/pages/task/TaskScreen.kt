package com.memksim.todo.ui.pages.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.memksim.todo.R
import com.memksim.todo.domain.interactor.UpdateDataInteractor
import com.memksim.todo.ui.utils.model.TaskItemUiState
import com.memksim.todo.ui.views.RepeatTaskButton
import com.memksim.todo.ui.views.TextInput
import com.memksim.todo.ui.pages.task.views.TaskAppBar
import com.memksim.todo.ui.pages.task.views.TaskFAB
import com.memksim.todo.ui.utils.MAIN_PAGE_DESTINATION
import com.memksim.todo.ui.utils.enums.Never

@ExperimentalComposeUiApi
@Composable
fun TaskScreen(
    navController: NavController,
    viewModel: TaskPageViewModel = hiltViewModel(),
    task: TaskItemUiState?
) {
    val title = remember { mutableStateOf(task?.title ?: "") }
    val note = remember { mutableStateOf(task?.note ?: "") }
    val date = remember { mutableStateOf(task?.date ?: "") }
    val time = remember { mutableStateOf(task?.time ?: "") }
    val repeat = remember { mutableStateOf(task?.repeat ?: "") }
    val isCompleted = remember { mutableStateOf(task?.isCompleted ?: "") }

    Scaffold(
        floatingActionButton = {
            TaskFAB {
                //todo onCLick
            }
        }

    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            TaskAppBar(){
                navController.run {
                    navigate(MAIN_PAGE_DESTINATION){
                        popBackStack()
                    }
                }
            }
            TextInput(
                value = title.value,
                hint = stringResource(id = R.string.task_name_hint),
                keyboardController = null,
                onValueChange = { newValue ->
                    title.value = newValue
                }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_comment),
                    contentDescription = stringResource(R.string.additional_info),
                    tint = Color.DarkGray
                )
                TextInput(
                    value = note.value,
                    hint = stringResource(id = R.string.add_additional_info),
                    keyboardController = null,
                    onValueChange = { newValue ->
                        note.value = newValue
                    }
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = stringResource(R.string.select_time),
                    tint = Color.DarkGray
                )
                OutlinedButton(
                    onClick = { /*datePicker.show()*/ },
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = date.value,
                        color = Color.Black
                    )
                }
                OutlinedButton(
                    onClick = { /*timePicker.show()*/ },
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = time.value,
                        color = Color.Black
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_repeat),
                    contentDescription = stringResource(R.string.repeat_task),
                    tint = Color.DarkGray
                )
                RepeatTaskButton() {
                    //todo
                }
            }
        }
    }
}
