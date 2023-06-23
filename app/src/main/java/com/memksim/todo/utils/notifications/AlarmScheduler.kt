package com.memksim.todo.utils.notifications

import com.memksim.todo.domain.model.Task

interface AlarmScheduler {

    fun schedule(item: Task)

    fun cancel(item: Task)

}