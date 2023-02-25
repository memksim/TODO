package com.memksim.todo.ui.pages.main.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.memksim.todo.R
import com.memksim.todo.ui.theme.AppMainColorLight

@Composable
fun MainAppBar() {
    val isMenuOpened = remember { mutableStateOf(false) }
    val isSubMenuOpened = remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.tasks),
                color = Color.White
            )
        },
        backgroundColor = AppMainColorLight,
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                painter = painterResource(id = R.drawable.ic_baseline_done_all),
                contentDescription = null,
                tint = Color.White
            )
        },
        actions = {
            IconButton(onClick = { isMenuOpened.value = isMenuOpened.value.not() }) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_more,
                    ),
                    contentDescription = stringResource(R.string.more_actions),
                    tint = Color.White
                )
            }
            DropdownMenu(
                expanded = isMenuOpened.value,
                onDismissRequest = { isMenuOpened.value = isMenuOpened.value.not() }
            ) {
                MainPageDropdownMenuItem(title = stringResource(id = R.string.settings)){/*todo*/}

                MainPageDropdownMenuItem(
                    title = stringResource(id = R.string.sort)
                ){
                    isSubMenuOpened.value = isSubMenuOpened.value.not()
                }

                DropdownMenu(
                    expanded = isSubMenuOpened.value,
                    onDismissRequest = { isSubMenuOpened.value = isSubMenuOpened.value.not() },
                    modifier = Modifier
                ) {
                    MainPageDropdownMenuItem(title = stringResource(R.string.by_alphabet)){/*todo*/}
                    MainPageDropdownMenuItem(title = stringResource(R.string.by_date_end)){/*todo*/}
                    MainPageDropdownMenuItem(title = stringResource(R.string.by_priority)){/*todo*/}
                }
            }
        }
    )
}

@Composable
fun MainPageDropdownMenuItem(
    title: String,
    subTitle: String = "",
    doOnClicked: () -> Unit
) {
    DropdownMenuItem(
        onClick = {
            doOnClicked()
        }
    ) {
        Column {
            Text(
                text = title,
                fontSize = 16.sp
            )
            if(!subTitle.isNullOrEmpty()){
                Text(
                    text = title,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun MainAppBarPreview() {
    MainAppBar()
}

