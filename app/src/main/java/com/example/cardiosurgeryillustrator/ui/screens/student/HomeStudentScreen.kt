package com.example.cardiosurgeryillustrator.ui.screens.student

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@ExperimentalMaterial3Api
@Composable
fun HomeStudentScreen(navController: NavController) {
    val recentStudies = remember {
        listOf(
            "Módulo 2 - 30% concluído",
            "Quiz 2 - 58% concluído"
        )
    }

    val quizzes = remember {
        listOf(
            Pair("Quiz 1", "58%"),
            Pair("Quiz 2", "0%"),
            Pair("Quiz 3", "Bloqueado")
        )
    }

    val modules = remember {
        listOf(
            Pair("Módulo 1", "58%"),
            Pair("Módulo 2", "0%"),
            Pair("Módulo 3", "Bloqueado")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Recent Studies Section
        Text(
            text = "Últimos estudos",
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(recentStudies) { study ->
                Card(
                    modifier = Modifier
                        .width(150.dp)
                        .height(100.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = study, style = MaterialTheme.typography.bodyMedium)
                        Button(
                            onClick = { /* Navegar para detalhes */ },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Continuar")
                        }
                    }
                }
            }
        }

        // Quizzes Section
        Text(
            text = "Meus Quizzes",
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(quizzes) { quiz ->
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = quiz.second, style = MaterialTheme.typography.headlineMedium)
                        Text(text = quiz.first, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }

        // Modules Section
        Text(
            text = "Meus Módulos",
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(modules) { module ->
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = module.second, style = MaterialTheme.typography.headlineMedium)
                        Text(text = module.first, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}