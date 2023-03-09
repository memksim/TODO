package com.memksim.todo.ui.pages.main

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memksim.todo.ui.pages.main.views.BottomSheetContent
import com.memksim.todo.ui.pages.main.views.MainAppBar
import com.memksim.todo.ui.pages.main.views.MainFAB
import com.memksim.todo.ui.pages.main.views.MainList
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(
    vm: MainPageViewModel
) {
    val pageState = vm.viewState.collectAsState()

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()

    if (pageState.value.toast.isNullOrEmpty().not()) {
        Toast.makeText(LocalContext.current, pageState.value.toast, Toast.LENGTH_SHORT).show()
    }

    BackHandler {
        coroutineScope.launch {
            setOnCloseListener(
                sheetState,
                vm,
                pageState.value.newTask
            )
        }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            BottomSheetContent(
                newItemUiState = pageState.value.newTask,
                onClose = {
                    coroutineScope.launch {
                        setOnCloseListener(
                            sheetState,
                            vm,
                            it
                        )
                    }
                },
                setDate = {},
                setRepeat = {},
                onSave = {
                    coroutineScope.launch {
                        if (sheetState.isVisible) sheetState.hide()
                        vm.handleEvent(MainPageViewModel.MainPageEvent.SaveNewTask(it))
                    }
                }
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
                tasks = pageState.value.tasks,
                paddingValues = it
            )
        }
    }
}

@ExperimentalMaterialApi
private suspend fun setOnCloseListener(
    sheetState: ModalBottomSheetState,
    vm: MainPageViewModel,
    item: MainPageItemUiState
) {
    if (sheetState.isVisible) {
        sheetState.hide()
        vm.handleEvent(
            MainPageViewModel.MainPageEvent.UpdateNewTaskInfo(
                title = item.title,
                note = item.note,
                date = item.date,
                time = item.time
            )
        )
    }
}
