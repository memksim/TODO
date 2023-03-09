package com.memksim.todo.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.memksim.todo.ui.pages.main.MainPageViewModel
import com.memksim.todo.ui.pages.main.MainScreen
import com.memksim.todo.ui.theme.ToDoTheme
import dagger.hilt.android.AndroidEntryPoint



@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainPageViewModel: MainPageViewModel by viewModels()
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {
                Surface {
                    MainScreen(
                        vm = mainPageViewModel
                    )
                }
            }
        }
    }
}
