package com.example.cardiosurgeryillustrator.ui.screens.notification

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.work.*
import com.example.cardiosurgeryillustrator.ui.view_models.NotificationViewModelFactory
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationSettingsScreen(
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val viewModel: NotificationViewModel = viewModel(
        factory = NotificationViewModelFactory(context)
    )

    val riskNotificationEnabled by viewModel.riskNotificationEnabled.collectAsState(initial = false)
    val habitsNotificationEnabled by viewModel.habitsNotificationEnabled.collectAsState(initial = false)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notificações") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                NotificationOption(
                    title = "Notificações de Risco",
                    description = "Receber alertas para situações de risco médico relacionadas às artérias.",
                    isEnabled = riskNotificationEnabled,
                    onToggle = { isChecked ->
                        viewModel.toggleRiskNotifications(isChecked)
                        if (isChecked) {
                            scheduleRiskNotifications(context)
                        } else {
                            cancelRiskNotifications(context)
                        }
                    }
                )
                HorizontalDivider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                NotificationOption(
                    title = "Notificações de Bons Hábitos",
                    description = "Receber notificações de bons hábitos para seguir durante o dia.",
                    isEnabled = habitsNotificationEnabled,
                    onToggle = { isChecked ->
                        viewModel.toggleHabitsNotifications(isChecked)
                        if (isChecked) {
                            scheduleHabitsNotifications(context)
                        } else {
                            cancelHabitsNotifications(context)
                        }
                    }
                )
                HorizontalDivider(color = Color(0xFFE0E0E0), thickness = 1.dp)
            }
        }
    }
}

@Composable
private fun NotificationOption(
    title: String,
    description: String,
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = isEnabled,
                onCheckedChange = onToggle,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF2196F3)
                )
            )
        }
        Text(
            description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            maxLines = 2
        )
    }
}

private fun scheduleRiskNotifications(context: Context) {
    val workRequest = PeriodicWorkRequestBuilder<RiskNotificationWorker>(
        15, TimeUnit.SECONDS
    ).build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "RiskNotifications",
        ExistingPeriodicWorkPolicy.UPDATE,
        workRequest
    )
}

private fun cancelRiskNotifications(context: Context) {
    WorkManager.getInstance(context).cancelUniqueWork("RiskNotifications")
}

private fun scheduleHabitsNotifications(context: Context) {
    val workRequest = PeriodicWorkRequestBuilder<HabitsNotificationWorker>(
        15, TimeUnit.SECONDS
    ).build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "HabitsNotifications",
        ExistingPeriodicWorkPolicy.UPDATE,
        workRequest
    )
}

private fun cancelHabitsNotifications(context: Context) {
    WorkManager.getInstance(context).cancelUniqueWork("HabitsNotifications")
}
