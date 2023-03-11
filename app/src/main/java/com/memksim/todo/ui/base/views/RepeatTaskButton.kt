package com.memksim.todo.ui.base.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.memksim.todo.R
import com.memksim.todo.ui.utils.enums.*

@ExperimentalComposeUiApi
@Composable
fun RepeatTaskButton(
    repeat: MutableState<Repeat> = mutableStateOf(Never),
    setRepeat: () -> Unit
) {
    OutlinedButton(
        onClick = { setRepeat() },
        modifier = Modifier
            .background(Color.Transparent)
            .padding(start = 16.dp)
    ) {
        Text(
            text = when (repeat.value) {
                is EveryDay -> stringResource(id = R.string.repeat_everyday)
                is EveryNDays -> pluralStringResource(
                    R.plurals.repeat_every_n_day,
                    (repeat.value as EveryNDays).days,
                    (repeat.value as EveryNDays).days
                )
                is EveryWeek -> stringResource(id = R.string.repeat_every_week)
                is EveryMonth -> stringResource(id = R.string.repeat_every_month)
                is EveryYear -> stringResource(id = R.string.repeat_every_year)
                is Never -> stringResource(id = R.string.repeat_never)
            },
            color = Color.Black
        )
    }
}
