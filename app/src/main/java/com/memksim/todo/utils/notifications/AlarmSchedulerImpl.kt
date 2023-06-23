package com.memksim.todo.utils.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.memksim.todo.domain.model.Task
import com.memksim.todo.utils.consts.PENDING_INTENT_EXTRA_TITLE
import java.time.ZoneId
import java.util.Calendar
import java.util.TimeZone

class AlarmSchedulerImpl(
    private val context: Context
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(item: Task) {
        val intent = Intent(context, AlarmBroadcastReceiver::class.java).apply {
            putExtra(PENDING_INTENT_EXTRA_TITLE, item.title)
        }
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC,
            System.currentTimeMillis() + 5000L,//todo
            PendingIntent.getBroadcast(
                context,
                item.id,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    override fun cancel(item: Task) {
        val intent = Intent(context, AlarmBroadcastReceiver::class.java).apply {
            putExtra(PENDING_INTENT_EXTRA_TITLE, item.title)
        }
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                item.id,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}