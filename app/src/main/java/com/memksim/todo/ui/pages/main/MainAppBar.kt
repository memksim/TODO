package com.memksim.todo.ui.pages.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memksim.todo.R
import com.memksim.todo.ui.theme.AppMainColorLight

@Composable
fun MainAppBar() {
    DefaultMainAppBar()
}

@Preview
@Composable
private fun MainAppBarPreview(){
    DefaultMainAppBar()
}

@Composable
fun DefaultMainAppBar() {
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
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_more,
                    ),
                    contentDescription = stringResource(R.string.more_actions),
                    tint = Color.White
                )
            }
        }
    )

}