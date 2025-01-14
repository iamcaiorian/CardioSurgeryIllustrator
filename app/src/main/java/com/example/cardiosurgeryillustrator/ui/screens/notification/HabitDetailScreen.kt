package com.example.cardiosurgeryillustrator.ui.screens.notification

import android.content.Context
import android.os.SystemClock
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore("habit_checklist")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitDetailScreen(
    title: String,
    description: String,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // DataStore keys
    val resetTimestampKey = longPreferencesKey("reset_timestamp")
    val habitsKeys = listOf(
        booleanPreferencesKey("exercise"),
        booleanPreferencesKey("hydration"),
        booleanPreferencesKey("stretching"),
        booleanPreferencesKey("fruit")
    )

    val habitsState = habitsKeys.map { key ->
        context.dataStore.data.map { prefs -> prefs[key] ?: false }
    }
    val habitResetTimestamp = context.dataStore.data.map { prefs ->
        prefs[resetTimestampKey] ?: 0L
    }.collectAsState(initial = 0L)

    val currentTime = SystemClock.elapsedRealtime()
    LaunchedEffect(habitResetTimestamp.value) {
        if (currentTime - habitResetTimestamp.value > 24 * 60 * 60 * 1000) {
            scope.launch {
                context.dataStore.edit { prefs ->
                    habitsKeys.forEach { prefs[it] = false }
                    prefs[resetTimestampKey] = currentTime
                }
            }
        }
    }

    val habits = habitsKeys.mapIndexed { index, key ->
        habitsState[index].collectAsState(initial = false)
    }

    fun toggleHabit(index: Int, checked: Boolean) {
        scope.launch {
            context.dataStore.edit { prefs -> prefs[habitsKeys[index]] = checked }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Marque os hábitos que você concluiu hoje. O progresso será reiniciado a cada 24 horas.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                val labels = listOf(
                    "Pratiquei exercícios no dia de hoje",
                    "Bebi pelo menos 2 litros de água",
                    "Me alonguei",
                    "Comi uma fruta"
                )
                habits.forEachIndexed { index, state ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = state.value,
                            onCheckedChange = { toggleHabit(index, it) }
                        )
                        Text(text = labels[index], style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}
