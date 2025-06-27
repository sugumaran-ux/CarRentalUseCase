package com.example.carrentalusecase


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : ComponentActivity() {

    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var speedGaugeView: SpeedGaugeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Initialize the views
        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        speedGaugeView = findViewById(R.id.speedGaugeView)



        // Set a click listener on the button
        button.setOnClickListener {
            val number = editText.text.toString().toFloatOrNull()
            if (number != null && number > 0) {
                speedGaugeView.setSpeed(number) // Limit to 10 for layout sanity

                createNotificationChannel()
            }
        }

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "speed_channel",
                "Speed Alerts",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifies when speed exceeds 100 km/h"
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }




}
