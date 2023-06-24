package com.memksim.todo.ui.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatMillisToDateString(
    millis: Long,
    pattern: String = DATE_PATTERN_WITHOUT_TIME
): String = SimpleDateFormat(pattern, Locale.getDefault()).format(Date(millis))

fun Int.toDateTimeItem(): String =
    if (this / 10 == 0) "0$this" else "$this"

const val FULL_DATE_TIME_PATTERN = "E, dd MMM, HH:mm"
const val DATE_PATTERN_WITHOUT_TIME = "E, dd MMM"
const val DATE_TIME_PATTERN_WITH_YEAR = "dd MMM YYYY, HH:mm"
const val DATE_PATTERN_WITH_YEAR = "dd MMM YYYY"
const val TIME_PATTERN = "HH:mm"