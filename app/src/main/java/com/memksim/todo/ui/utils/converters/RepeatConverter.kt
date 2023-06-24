package com.memksim.todo.ui.utils.converters

import com.memksim.todo.ui.utils.enums.EveryDay
import com.memksim.todo.ui.utils.enums.EveryMonth
import com.memksim.todo.ui.utils.enums.EveryNDays
import com.memksim.todo.ui.utils.enums.EveryNWeek
import com.memksim.todo.ui.utils.enums.EveryWeek
import com.memksim.todo.ui.utils.enums.Never
import com.memksim.todo.ui.utils.enums.Repeat
import com.memksim.todo.utils.consts.REPEAT_EVERYDAY
import com.memksim.todo.utils.consts.REPEAT_EVERY_MONTH
import com.memksim.todo.utils.consts.REPEAT_EVERY_N_DAY
import com.memksim.todo.utils.consts.REPEAT_EVERY_N_WEEK
import com.memksim.todo.utils.consts.REPEAT_EVERY_WEEK
import com.memksim.todo.utils.consts.REPEAT_NEVER

fun Repeat.getRepeatCode(): Int =
    when(this) {
        is Never -> {
            REPEAT_NEVER
        }
        is EveryDay -> {
            REPEAT_EVERYDAY
        }
        is EveryNDays -> {
            REPEAT_EVERY_N_DAY + n
        }
        is EveryWeek -> {
            REPEAT_EVERY_WEEK
        }
        is EveryNWeek -> {
            REPEAT_EVERY_N_WEEK + n
        }
        is EveryMonth -> {
            REPEAT_EVERY_MONTH
        }
    }

fun getRepeat(repeatCode: Int): Repeat =
    when(repeatCode) {
        0 -> Never
        1 -> EveryDay
        2 -> EveryWeek
        3 -> EveryMonth
        else -> {
            if(repeatCode - 100 in 1..7) {
                EveryNDays(repeatCode - 100)
            } else {
                if(repeatCode - 200 in 1..7) {
                    EveryNWeek(repeatCode - 200)
                } else {
                    Never
                }
            }
        }
    }