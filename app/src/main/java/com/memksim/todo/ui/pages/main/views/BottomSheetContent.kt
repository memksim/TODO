package com.memksim.todo.ui.pages.main.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.memksim.todo.R
import com.memksim.todo.ui.theme.AppMainColorLight
import com.memksim.todo.ui.theme.AppSecondColorLight
import com.memksim.todo.ui.utils.enums.*
import java.util.*

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun BottomSheetContent(
    onClose: () -> Unit,
    setDate: () -> Unit,
    setRepeat: () -> Unit
) {
    val taskName = remember {
        mutableStateOf("")
    }
    val additionalInfo = remember {
        mutableStateOf("")
    }
    val date = remember {
        mutableStateOf("пт, 13 март., 12:00")
    }
    val isAdditionalInfoNeeded = remember {
        mutableStateOf(false)
    }
    val isBookmarked = remember {
        mutableStateOf(false)
    }
    val repeat = remember {
        mutableStateOf<Repeat?>(EveryNDays(10))
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        IconButton(
            onClick = { onClose() }
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = stringResource(id = R.string.close),
                tint = Color.LightGray
            )
        }
        TextInput(
            value = taskName,
            hint = stringResource(id = R.string.task_name_hint)
        )

        if (isAdditionalInfoNeeded.value) {
            TextInput(
                value = additionalInfo,
                hint = stringResource(id = R.string.add_additional_info),
                fontSize = 12.sp
            )
        }

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {

            IconButton(onClick = { setRepeat() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_event_repeat),
                    contentDescription = stringResource(R.string.repeat_task),
                    tint = Color.DarkGray
                )
            }

            IconButton(
                onClick = {
                    isBookmarked.value = !isBookmarked.value
                    //todo отображать тост
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = stringResource(R.string.remove_from_bookmarks),
                    tint = if (isBookmarked.value) Color.Yellow else Color.DarkGray
                )

            }

            IconButton(
                onClick = { isAdditionalInfoNeeded.value = !isAdditionalInfoNeeded.value }
            ) {
                Icon(
                    painter = if (isAdditionalInfoNeeded.value) {
                        painterResource(id = R.drawable.ic_comment_disabled)
                    } else {
                        painterResource(id = R.drawable.ic_comment)
                    },
                    contentDescription = stringResource(R.string.additional_info),
                    tint = Color.DarkGray
                )
            }

            IconButton(
                onClick = { setDate() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = stringResource(R.string.additional_info),
                    tint = Color.DarkGray
                )
            }

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Column() {
                if (date.value.isNotEmpty()) {
                    OutlinedButton(
                        onClick = { setDate() },
                        modifier = Modifier.background(Color.Transparent)
                    ) {
                        Text(
                            text = date.value,
                            color = Color.Black
                        )
                    }
                }
                if (repeat.value != null) {
                    RepeatTaskButton(
                        repeat = repeat,
                        setRepeat = setRepeat
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            TextButton(
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = stringResource(id = R.string.save),
                    color = AppSecondColorLight
                )
            }
        }

    }
}

@ExperimentalComposeUiApi
@Composable
private fun RepeatTaskButton(
    repeat: MutableState<Repeat?>,
    setRepeat: () -> Unit
){
    OutlinedButton(
        onClick = { setRepeat() },
        modifier = Modifier.background(Color.Transparent)
    ) {
        Text(
            text = when (repeat.value!!) {
                is EveryDay -> stringResource(id = R.string.repeat_everyday)
                is EveryNDays -> pluralStringResource(
                    R.plurals.repeat_every_n_day,
                    (repeat.value as EveryNDays).days,
                    (repeat.value as EveryNDays).days
                )
                is EveryWeek -> stringResource(id = R.string.repeat_every_week)
                is EveryMonth -> stringResource(id = R.string.repeat_every_month)
                is EveryYear -> stringResource(id = R.string.repeat_every_year)
            },
            color = Color.Black
        )
    }
}

@Composable
private fun TextInput(
    value: MutableState<String>,
    hint: String,
    fontSize: TextUnit = 16.sp
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value.value,
        onValueChange = { newValue ->
            value.value = newValue
        },
        placeholder = {
            Text(
                text = hint,
                fontSize = fontSize
            )
        },
        textStyle = TextStyle(
            fontSize = fontSize
        ),
        label = null,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent,
            cursorColor = Color.DarkGray,
            textColor = Color.DarkGray
        ),
        singleLine = true
    )
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Preview
@Composable
fun BottomSheetContentPreview() {
    BottomSheetContent(
        {},
        {},
        {}
    )
}