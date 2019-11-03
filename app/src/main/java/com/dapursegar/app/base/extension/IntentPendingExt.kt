package com.dapursegar.app.base.extension

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Ramana on 11-Oct-19.
 */

fun Context.getAlarmService(): AlarmManager {
    return getSystemService(Context.ALARM_SERVICE) as AlarmManager
}

fun <T> Context.setAlarm(
    id: Int,
    receiver: Class<T>,
    schedule: Calendar
): PendingIntent {
    val alarmIntent = Intent(this, receiver)
    val pendingIntent = PendingIntent.getBroadcast(
        this, id, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT
    )
    if (schedule.before(Calendar.getInstance())) schedule.add(Calendar.DATE, 1)
    when {
        Build.VERSION.SDK_INT <= Build.VERSION_CODES.M -> {
            getAlarmService().setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                schedule.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
            getAlarmService().setRepeating(
                AlarmManager.RTC_WAKEUP,
                schedule.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
    }
    return pendingIntent
}

fun <T> Context.cancelAlarm(
    serviceId: Int,
    service: Class<T>
): PendingIntent {
    val myIntent = Intent(this, service)
    val pendingIntent = PendingIntent.getBroadcast(
        this, serviceId, myIntent, PendingIntent.FLAG_CANCEL_CURRENT
    )
    getAlarmService().cancel(pendingIntent)
    pendingIntent.cancel()
    return pendingIntent
}

fun <T> Context.startBroadcastReceiver(
    serviceId: Int,
    receiver: Class<T>,
    schedule: Calendar
): PendingIntent {
    val myIntent = Intent(this, receiver)
    val pendingIntent = PendingIntent.getBroadcast(
        this, serviceId, myIntent, PendingIntent.FLAG_UPDATE_CURRENT
    )
    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.setRepeating(
        AlarmManager.RTC_WAKEUP,
        schedule.timeInMillis,
        AlarmManager.INTERVAL_DAY,
        pendingIntent
    )
    return pendingIntent
}

fun <T> Context.startPendingIntentActivity(
    intentId: Int,
    service: Class<T>
): PendingIntent {
    val myIntent = Intent(this, service)
    return PendingIntent.getActivity(this, intentId, myIntent, PendingIntent.FLAG_UPDATE_CURRENT)
}

fun scheduleCalendar(hours: Int, minutes: Int): Calendar {
    return Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR, hours)
        set(Calendar.MINUTE, minutes)
        set(Calendar.SECOND, 0)
        set(Calendar.AM_PM, Calendar.AM)
    }
}

fun scheduleCalendar(hours: Int): Calendar {
    return Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR, hours)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.AM_PM, Calendar.AM)
    }
}

fun Context.cancelAlarm(pendingIntent: PendingIntent) {
    getAlarmService().cancel(pendingIntent)
    pendingIntent.cancel()
}

fun Context.setupAlarm(pendingIntent: PendingIntent, schedule: Calendar) {
    when {
        Build.VERSION.SDK_INT <= Build.VERSION_CODES.M -> {
            getAlarmService().setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                schedule.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
            getAlarmService().setRepeating(
                AlarmManager.RTC_WAKEUP,
                schedule.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
    }
}

fun Context.setupAlarm(
    pendingIntent: PendingIntent,
    schedule: Calendar,
    intervalSeconds: Long
) {
    getAlarmService().setRepeating(
        AlarmManager.RTC_WAKEUP,
        schedule.timeInMillis,
        TimeUnit.SECONDS.toMillis(intervalSeconds),
        pendingIntent
    )
}