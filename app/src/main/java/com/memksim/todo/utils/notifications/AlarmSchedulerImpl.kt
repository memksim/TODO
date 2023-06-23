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
        Log.d("ToDoAppDebug", "schedule")
        val intent = Intent(context, AlarmBroadcastReceiver::class.java).apply {
            putExtra(PENDING_INTENT_EXTRA_TITLE, item.title)
        }
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC,
            Calendar.getInstance(TimeZone.getDefault()).time.time + 5000L,
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