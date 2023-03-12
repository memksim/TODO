package com.memksim.todo.ui.pages.main

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.memksim.todo.base.consts.TAG
import com.memksim.todo.ui.utils.model.TaskItemUiState
import com.memksim.todo.ui.pages.main.views.BottomSheetContent
import com.memksim.todo.ui.pages.main.views.MainAppBar
import com.memksim.todo.ui.pages.main.views.MainFAB
import com.memksim.todo.ui.pages.main.views.MainList
import com.memksim.todo.ui.utils.TASK_PAGE_DESTINATION
import com.memksim.todo.ui.utils.TASK_PAGE_DESTINATION_ARGUMENT_TASK
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(
    viewModel: MainPageViewModel = hiltViewModel(),
    navController: NavController
) {
    val pageState = viewModel.viewState.collectAsState()

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    if (sheetState.isVisible.not()) {
        focusManager.clearFocus()
        keyboardController?.hide()
    }

    val coroutineScope = rememberCoroutineScope()

    if (pageState.value.toast.isNullOrEmpty().not()) {
        Toast.makeText(LocalContext.current, pageState.value.toast, Toast.LENGTH_SHORT).show()
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            BottomSheetContent(
                newItemUiState = pageState.value.newTask,
                onClose = {
                    coroutineScope.launch {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                        setOnCloseListener(
                            sheetState = sheetState,
                            vm = viewModel,
                            item = it
                        )
                    }
                },
                setRepeat = {/*todo*/ },
                onSave = {
                    coroutineScope.launch {
                        if (sheetState.isVisible) sheetState.hide()
                        viewModel.handleEvent(MainPageViewModel.MainPageEvent.SaveNewTask(it))
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
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
                paddingValues = it,
                onCompleteTask = { task ->
                    viewModel.handleEvent(MainPageViewModel.MainPageEvent.CompleteTask(task = task))
                },
                onClick = { task ->
                    Log.d(TAG, "MainScreen: onClick navigate $task")
                    navController.run {
                        navigate(TASK_PAGE_DESTINATION){
                            popBackStack()
                        }
                        currentBackStackEntry?.arguments?.putParcelable(
                            TASK_PAGE_DESTINATION_ARGUMENT_TASK,
                            task
                        )
                    }
                }
            )
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
private suspend fun setOnCloseListener(
    sheetState: ModalBottomSheetState,
    vm: MainPageViewModel,
    item: TaskItemUiState
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
