package com.example.cardiosurgeryillustrator.ui.screens.notification


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.cardiosurgeryillustrator.R

class RiskNotificationWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val messages = listOf(
            "Consulte um médico para verificar suas artérias!",
            "Cuidado com sintomas como dores no peito.",
            "Evite esforços físicos intensos se estiver em risco.",
            "Monitore regularmente sua pressão arterial.",
            "Mantenha uma dieta saudável e equilibrada."
        )
        sendNotification("Alerta de Risco", messages.random())
        return Result.success()
    }

    private fun sendNotification(title: String, message: String) {
        val notificationManager = applicationContext.getSystemService(NotificationManager::class.java)
        val channelId = "risk_notifications"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Alerta de Risco", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.heart_icon)
            .build()

        notificationManager.notify(1, notification)
    }
}
