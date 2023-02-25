package com.memksim.todo.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.memksim.todo.ui.pages.main.MainScreen
import com.memksim.todo.ui.theme.ToDoTheme
import com.memksim.todo.ui.base.TaskItemUiState
import dagger.hilt.android.AndroidEntryPoint

val tasksList = listOf(
    TaskItemUiState(
        title = "Купить хлеб",
        date = "02.02.2023",
        time = "15:30"
    ),
    TaskItemUiState(
        title = "Сделать",
        note = "То сё 5 10",
        date = "Завтра",
        time = "20:00"
    ),
    TaskItemUiState(
        title = "Позвонить маме",
        note = "заметка3",
        date = "Сегодня",
        time = "18:00"
    ),
    TaskItemUiState(
        title = "Купить",
        note = "То сё 5 10",
        date = "Послезавтра",
        time = "12:00"
    ),
    TaskItemUiState(
        title = "Купить спрей",
        date = "10.02.2023",
        time = "12:00"
    ),
)

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {
                Surface {
                    MainScreen(
                        tasks = tasksList
                    )
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Preview
@Composable
fun MainPreview(){
    Surface {
        MainScreen(tasksList)
    }
}
