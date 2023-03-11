package com.memksim.todo.ui.utils.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Repeat: Parcelable

object Never: Repeat()
object EveryDay: Repeat()
class EveryNDays(val days: Int): Repeat()
object EveryWeek: Repeat()
object EveryMonth: Repeat()
object EveryYear: Repeat()
