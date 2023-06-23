package com.memksim.todo.utils.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.memksim.todo.R
import com.memksim.todo.utils.consts.NOTIFICATION_CHANNEL_ID
import com.memksim.todo.utils.consts.PENDING_INTENT_EXTRA_TITLE

class AlarmBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        buildNotification(context)
    }

    private fun buildNotification(context: Context?) {
        context ?: return
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("test")
            .setContentText("content")
            .setSmallIcon(R.drawable.ic_logo)
            .setPriority(NotificationCompat.PRIORITY_HIGH )
            .build()
        NotificationManagerCompat.from(context).notify(
            101,
            notification
        )
    }
}