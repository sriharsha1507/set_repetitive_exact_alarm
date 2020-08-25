package com.foodiedev.setrepetitiveexactalarm.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.foodiedev.setrepetitiveexactalarm.util.Constants
import io.karn.notify.Notify

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Constants.ACTION_SET_EXACT -> {
                Notify
                    .with(context)
                    .content {
                        title = "Set Exact Time"
                        text = "I got triggered"
                    }
                    .show()
            }
        }
    }
}