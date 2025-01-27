package com.example.cardiosurgeryillustrator.ui.screens.student.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.cardiosurgeryillustrator.MainActivity
import com.example.cardiosurgeryillustrator.R

class HabitsNotificationWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val habits = listOf(
            Pair(
                "Beba água!",
                "Manter-se hidratado é essencial para o funcionamento do corpo. Consuma pelo menos 2 litros de água por dia."
            ),
            Pair(
                "Coma frutas para se manter saudável!",
                "Frutas são ricas em vitaminas, minerais e fibras que fortalecem o sistema imunológico e ajudam no funcionamento do organismo. "
            ),
            Pair(
                "Evite gorduras e alimentos ultraprocessados.",
                "O consumo excessivo de alimentos ultraprocessadospode levar a problemas como obesidade e doenças cardiovasculares."
            ),
            Pair(
                "Faça uma pausa para se alongar!",
                "O alongamento regular reduz tensões musculares, melhora a postura e aumenta a circulação sanguínea. "
            ),
            Pair(
                "Pratique exercícios regularmente.",
                "A prática de exercícios físicos fortalece o coração, melhora a saúde mental e aumenta a resistência física. "
            )
        )
        val (title, description) = habits.random()
        sendNotification(title, description)
        return Result.success()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun sendNotification(title: String, description: String) {
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            putExtra("navigate_to", "habit_detail")
            putExtra("habit_title", title)
            putExtra("habit_description", description)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "habits_notifications"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Bons Hábitos",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.heart_icon)
            .setContentTitle(title)
            .setContentText(description)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)
    }
}
