package com.example.notification

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class NotificationUtils : BroadcastReceiver(){
    val CHANNEL_ID = "default"

    override fun onReceive(context: Context?, intent: Intent?) {
        notificationWithTapAction(context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(context: Context) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelDescription = context.getString(R.string.notif_channel_description)
        val channelName = context.getString(R.string.notif_channel_name)
        val channel = NotificationChannel(
            CHANNEL_ID,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = channelDescription
            enableLights(true)
            lightColor = Color.GREEN
            enableVibration(true)
        }
        notificationManager.createNotificationChannel(channel)
    }

    private fun getContentIntent(context: Context): PendingIntent? {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return PendingIntent.getActivity(context, 123, intent, 0)
    }


//    fun notificationSimple(context: Context){
//
//
//        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
//            createNotificationChannel(context)
//        }
//        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_launcher_background)
//            .setContentTitle("Esse é meu titulooooo")
//            .setContentText("saflçhs")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setDefaults(Notification.DEFAULT_ALL)
//        val notificationManager = NotificationManagerCompat.from(context)
//        notificationManager.notify(1,notificationBuilder.build())
//    }

    fun notificationWithTapAction(context: Context?){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            if (context != null) {
                createNotificationChannel(context)
            }
        }
        val notificationBuilder = context?.let {
            NotificationCompat.Builder(it, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Pós")
                .setContentText("meu textinho0000000")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(getContentIntent(context))
                .setAutoCancel(true)
        }
        val notificationManager = context?.let { NotificationManagerCompat.from(it) }
        if (notificationManager != null) {
            if (notificationBuilder != null) {
                notificationManager.notify(2,notificationBuilder.build())
            }
        }
    }


}