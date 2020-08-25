package com.foodiedev.setrepetitiveexactalarm.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.foodiedev.setrepetitiveexactalarm.receiver.AlarmReceiver
import com.foodiedev.setrepetitiveexactalarm.util.Constants
import com.foodiedev.setrepetitiveexactalarm.util.RandomUtil
import java.util.*

class AlarmService(private val context: Context) {
    private val alarmManager: AlarmManager? =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?


    fun setExactAlarm(timeInMillis: Long) {
        val pendingIntent =
            PendingIntent.getBroadcast(
                context,
                getRandomRequestCode(),
                getIntent().apply {
                    action = Constants.ACTION_SET_EXACT
                    putExtra(Constants.ACTION_SET_EXACT, 0L)
                },
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        alarmManager?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    timeInMillis,
                    pendingIntent
                )
            } else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    timeInMillis,
                    pendingIntent
                )
            }
        }
    }

    private fun getIntent() = Intent(context, AlarmReceiver::class.java)

    private fun getRandomRequestCode() = RandomUtil.getRandomInt()

}