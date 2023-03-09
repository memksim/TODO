package com.memksim.todo.ui.pages.main.views

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.memksim.todo.R
import com.memksim.todo.ui.pages.main.MainPageItemUiState
import com.memksim.todo.ui.theme.AppSecondColorLight
import com.memksim.todo.ui.utils.enums.*
import com.memksim.todo.ui.utils.toDateTimeItem
import java.util.*

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun BottomSheetContent(
    newItemUiState: MainPageItemUiState,
    onClose: (MainPageItemUiState) -> Unit,
    setRepeat: () -> Unit,
    onSave: (MainPageItemUiState) -> Unit,
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    // Fetching current year, month and day
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentYear = calendar.get(Calendar.YEAR)
    // Fetching current hour, and minute
    val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
    val currentMins = calendar.get(Calendar.MINUTE)
    val taskName = remember {
        mutableStateOf(newItemUiState.title)
    }
    val additionalInfo = remember {
        mutableStateOf(newItemUiState.note)
    }
    val date = remember {
        mutableStateOf(
            String.format(
                Locale.getDefault(),
                context.getString(R.string.formatted_date),
                currentDay.toDateTimeItem(),
                currentMonth.toDateTimeItem(),
                currentYear.toDateTimeItem()
            )
        )
    }
    val time = remember {
        mutableStateOf(
            String.format(
                Locale.getDefault(),
                context.getString(R.string.formatted_time),
                currentHour.toDateTimeItem(),
                currentMins.toDateTimeItem()
            )
        )
    }
    val isAdditionalInfoNeeded = remember {
        mutableStateOf(false)
    }
    val isBookmarked = remember {
        mutableStateOf(false)
    }
    val repeat = remember {
        mutableStateOf<Repeat?>(null)
    }
    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = String.format(
                Locale.getDefault(),
                context.getString(R.string.formatted_date),
                dayOfMonth.toDateTimeItem(),
                month.toDateTimeItem(),
                year.toDateTimeItem()
            )
        },
        currentYear,
        currentMonth,
        currentDay
    )
    val timePicker = TimePickerDialog(
        context,
        { _: TimePicker, hour: Int, min: Int ->
            time.value = String.format(
                Locale.getDefault(),
                context.getString(R.string.formatted_time),
                hour.toDateTimeItem(),
                min.toDateTimeItem()
            )
        },
        currentHour,
        currentMins,
        true
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        IconButton(
            onClick = {
                onClose(
                    MainPageItemUiState(
                        title = taskName.value,
                        note = additionalInfo.value,
                        date = date.value
                    )
                )
            }
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = stringResource(id = R.string.close),
                tint = Color.LightGray
            )
        }
        TextInput(
            value = taskName.value,
            hint = stringResource(id = R.string.task_name_hint),
            keyboardController = LocalSoftwareKeyboardController.current
        ) { newValue ->
            taskName.value = newValue
        }

        if (isAdditionalInfoNeeded.value) {
            TextInput(
                value = additionalInfo.value,
                hint = stringResource(id = R.string.add_additional_info),
                fontSize = 12.sp,
                keyboardController = LocalSoftwareKeyboardController.current
            ) { newValue ->
                additionalInfo.value = newValue
            }
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

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            OutlinedButton(
                onClick = { datePicker.show() },
                modifier = Modifier.background(Color.Transparent)
            ) {
                Text(
                    text = date.value,
                    color = Color.Black
                )
            }
            OutlinedButton(
                onClick = { timePicker.show() },
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = time.value,
                    color = Color.Black
                )
            }
            if (repeat.value != null) {
                RepeatTaskButton(
                    repeat = repeat,
                    setRepeat = setRepeat
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            TextButton(
                onClick = {
                    onSave(
                        MainPageItemUiState(
                            title = taskName.value,
                            note = additionalInfo.value,
                            date = date.value,
                            time = time.value
                        )
                    )
                }
            ) {
                Text(
                    text = stringResource(id = R.string.save),
                    color = AppSecondColorLight
                )
            }
        }

    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
@Preview
fun BottomSheetContentPreview() {
    BottomSheetContent(
        newItemUiState = MainPageItemUiState(
            title = "Тестовая задачка",
            date = "10.03.2023",
            time = "16:00"
        ),
        onClose = {},
        setRepeat = { /*TODO*/ },
        onSave = {}
    )
}