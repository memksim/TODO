package com.memksim.todo.ui.pages.main

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.memksim.todo.base.tasksList
import com.memksim.todo.view.model.TaskItemUiState

@ExperimentalMaterialApi
@Composable
fun MainScreen(tasks: List<TaskItemUiState>) {
    Scaffold(
        topBar = {
            MainAppBar()
        },
        floatingActionButton = {
            MainFAB()
        }
    ){
        MainList(
            tasks = tasks,
            paddingValues = it
        )
    }
}


@ExperimentalMaterialApi
@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(tasksList)
}
