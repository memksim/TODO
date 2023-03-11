package com.memksim.todo.ui.pages.task.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
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
fun TaskAppBar() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(all = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
            )
        }
        Row() {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star_outline),
                    contentDescription = stringResource(id = R.string.add_to_bookmarks)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = stringResource(id = R.string.delete)
                )
            }
        }
    }
}

@Preview
@Composable
private fun TaskAppBarPreview() {

}