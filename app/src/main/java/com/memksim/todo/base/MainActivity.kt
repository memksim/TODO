package com.memksim.todo.base

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.memksim.todo.ui.pages.main.MainPageViewModel
import com.memksim.todo.ui.pages.main.MainScreen
import com.memksim.todo.ui.pages.task.TaskScreen
import com.memksim.todo.ui.theme.ToDoTheme
import com.memksim.todo.ui.utils.MAIN_PAGE_DESTINATION
import com.memksim.todo.ui.utils.TASK_PAGE_DESTINATION
import com.memksim.todo.ui.utils.TASK_PAGE_DESTINATION_ARGUMENT_TASK
import com.memksim.todo.ui.utils.model.TaskItemUiState
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {
                Scaffold {
                    it
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = MAIN_PAGE_DESTINATION
                    ) {
                        composable(route = MAIN_PAGE_DESTINATION) {
                            MainScreen(navController = navController)
                        }
                        composable(route = TASK_PAGE_DESTINATION) { entry ->
                            TaskScreen(
                                navController = navController,
                                task = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    entry.arguments?.getParcelable(
                                        TASK_PAGE_DESTINATION_ARGUMENT_TASK,
                                        TaskItemUiState::class.java
                                    )
                                } else {
                                    entry.arguments?.getParcelable(
                                        TASK_PAGE_DESTINATION_ARGUMENT_TASK
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
