package com.example.cardiosurgeryillustrator.ui.screens.patient.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.cardiosurgeryillustrator.R
import java.text.DateFormat
import java.util.Date

class AppointmentNotificationWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        Log.d("AppointmentNotificationWorker", "Iniciando o Worker de notificação.")

        val appointmentDate = inputData.getLong("appointment_date", 0L)
        Log.d("appointment date", "$appointmentDate")
        if (appointmentDate == 0L) {
            Log.e("AppointmentNotificationWorker", "Data de consulta inválida!")
            return Result.failure()
        }

        val currentDateMillis = System.currentTimeMillis()

        if (appointmentDate <= currentDateMillis) {
            Log.d("AppointmentNotificationWorker", "A consulta é para hoje ou uma data passada. Nenhuma notificação será enviada.")
            return Result.success()
        }
        val date = Date(appointmentDate)
        val formattedDate = DateFormat.getDateInstance().format(date)

        Log.d("AppointmentNotification", "Sending notification for date: $formattedDate")

        // Envie a notificação
        sendNotification(formattedDate)

        return Result.success()
    }

    private fun sendNotification(appointmentDate: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "appointment_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Agenda de Consultas",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
            Log.d("AppointmentNotification", "Notification channel created")
        }

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.heart_icon)
            .setContentTitle("Lembrete de Consulta")
            .setContentText("Você terá uma consulta na data $appointmentDate.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()


        notificationManager.notify(1, notification)
        Log.d("AppointmentNotification", "Notification sent for date: $appointmentDate")
    }
}
