package com.memksim.todo.utils.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.memksim.todo.utils.consts.PENDING_INTENT_EXTRA_TITLE

class AlarmBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra(PENDING_INTENT_EXTRA_TITLE) ?: return
    }
}