package com.memksim.todo.ui.pages.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memksim.todo.base.tasksList
import com.memksim.todo.ui.base.TaskItemUiState
import com.memksim.todo.ui.pages.main.views.BottomSheetContent
import com.memksim.todo.ui.pages.main.views.MainAppBar
import com.memksim.todo.ui.pages.main.views.MainFAB
import com.memksim.todo.ui.pages.main.views.MainList
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(tasks: List<TaskItemUiState>) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()

    BackHandler {
        coroutineScope.launch {
            sheetState.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            BottomSheetContent(
                onClose = {
                    coroutineScope.launch {
                        if (sheetState.isVisible) sheetState.hide()
                    }
                },
                setDate = {},
                setRepeat = {}
            )
        },
        modifier = Modifier.fillMaxSize(),
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Scaffold(
            topBar = {
                MainAppBar()
            },
            floatingActionButton = {
                MainFAB {
                    coroutineScope.launch {
                        if (sheetState.isVisible) sheetState.hide()
                        else sheetState.show()
                    }
                }
            }
        ) {
            MainList(
                tasks = tasks,
                paddingValues = it
            )
        }
    }
}


@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(tasksList)
}
