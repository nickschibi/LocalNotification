package com.example.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var am : AlarmManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        am = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var data : String = "2020/02/05"
        var hora : String = "11:06"

        var sdf : SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm")

        var calendar : Calendar = Calendar.getInstance()
        calendar.setTime(sdf.parse(data + " " + hora))
        var time = calendar.timeInMillis

        var btnSimple = findViewById<Button>(R.id.notButton)

        btnSimple.setOnClickListener {
            val alarmIntent = Intent(this, NotificationUtils::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, 123, alarmIntent, 0)
            val manager =
                getSystemService(Context.ALARM_SERVICE) as AlarmManager
            manager.set(AlarmManager.RTC_WAKEUP,
                 time,pendingIntent)
        }
    }
}
