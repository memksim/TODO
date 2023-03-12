package com.memksim.todo.ui.pages.task

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.memksim.todo.R
import com.memksim.todo.ui.utils.model.TaskItemUiState
import com.memksim.todo.ui.views.RepeatTaskButton
import com.memksim.todo.ui.views.TextInput
import com.memksim.todo.ui.pages.task.views.TaskAppBar
import com.memksim.todo.ui.pages.task.views.TaskFAB
import com.memksim.todo.ui.utils.MAIN_PAGE_DESTINATION

@ExperimentalComposeUiApi
@Composable
fun TaskScreen(
    navController: NavController,
    viewModel: TaskPageViewModel = hiltViewModel(),
    task: TaskItemUiState?
) {
    if(task != null) {
        viewModel.stateTask(task = task)
    }
    val uiState = viewModel.viewState.collectAsState()
        .apply {
            with(value){
                if(toast != null){
                    Toast.makeText(LocalContext.current, toast, Toast.LENGTH_SHORT).show()
                }
            }
        }

    val title = remember { mutableStateOf(uiState.value.task.title) }
    val note = remember { mutableStateOf(uiState.value.task.note.ifBlank { "" }) }
    val date = remember { mutableStateOf(uiState.value.task.date) }
    val time = remember { mutableStateOf(uiState.value.task.time) }
    val repeat = remember { mutableStateOf(uiState.value.task.repeat) }

    if(task != null) {
        Scaffold(
            floatingActionButton = {
                TaskFAB {
                    viewModel.handleEvent(TaskPageViewModel.TaskPageUiEvent.CompleteTask)
                    navigateBack(navController = navController)
                }
            }

        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {
                TaskAppBar(
                    navigateBack = {
                        viewModel.handleEvent(
                            TaskPageViewModel.TaskPageUiEvent.UpdateTask(
                                title = title.value,
                                note = note.value,
                                date = date.value,
                                time = time.value,
                                repeat = repeat.value
                            )
                        )
                        navigateBack(navController = navController)
                    },
                    bookmarkTask = {
                        //todo
                    },
                    deleteTask = {
                        viewModel.handleEvent(TaskPageViewModel.TaskPageUiEvent.DeleteTask)
                        navigateBack(navController = navController)
                    }
                )
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
                        onValueChange = { newValue: String ->
                            note.value = newValue.ifEmpty { " " }
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
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.something_gones_wrong),
                textAlign = TextAlign.Center
            )
            Button(
                onClick = { navigateBack(navController = navController) }
            ) {
                Text(text = stringResource(R.string.navigate_back))
            }
        }

    }
}

private fun navigateBack(
    navController: NavController
) {
    navController.run {
        navigate(MAIN_PAGE_DESTINATION){
            popBackStack()
        }
    }
}
