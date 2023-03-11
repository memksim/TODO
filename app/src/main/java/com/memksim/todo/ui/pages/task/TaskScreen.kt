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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memksim.todo.R
import com.memksim.todo.ui.base.views.RepeatTaskButton
import com.memksim.todo.ui.base.views.TextInput
import com.memksim.todo.ui.pages.task.views.TaskAppBar
import com.memksim.todo.ui.pages.task.views.TaskFAB

@ExperimentalComposeUiApi
@Composable
fun TaskScreen(){
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
            TaskAppBar()
            TextInput(
                value = "Название задачи",
                hint = stringResource(id = R.string.task_name_hint),
                keyboardController = null,
                onValueChange = {}
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
                    value = "",
                    hint = stringResource(id = R.string.add_additional_info),
                    keyboardController = null,
                    onValueChange = {}
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
                        text = "23.04.2023",
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
                        text = "12:00",
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

@ExperimentalComposeUiApi
@Preview
@Composable
private fun TaskScreenPreview(){
    TaskScreen()
}