package com.memksim.todo.ui.pages.task.views

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.memksim.todo.R
import com.memksim.todo.ui.theme.AppSecondColorLight

@Composable
fun TaskFAB(
    onClick: () -> Unit
){
    FloatingActionButton(
        onClick = { onClick() },
        backgroundColor = AppSecondColorLight
    ) {
        Icon(
            imageVector = Icons.Filled.Done,
            contentDescription = stringResource(R.string.done),
            tint = Color.White
        )
    }
}