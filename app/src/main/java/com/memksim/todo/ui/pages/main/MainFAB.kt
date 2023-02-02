package com.memksim.todo.ui.pages.main

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.memksim.todo.R
import com.memksim.todo.ui.theme.AppSecondColorLight

@Composable
fun MainFAB() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        backgroundColor = AppSecondColorLight
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = stringResource(R.string.new_task),
            tint = Color.White
        )
    }
}