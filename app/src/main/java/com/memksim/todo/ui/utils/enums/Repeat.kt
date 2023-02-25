package com.memksim.todo.ui.utils.enums

sealed class Repeat

object EveryDay: Repeat()
class EveryNDays(val days: Int): Repeat()
object EveryWeek: Repeat()
object EveryMonth: Repeat()
object EveryYear: Repeat()
