package com.example.cardiosurgeryillustrator.ui.components.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.Quiz

@Composable
fun CardQuiz(
    quiz: Quiz,
    onEdit: (Quiz) -> Unit,
    onDelete: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)), // Gray padronizado
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(quiz.title, style = MaterialTheme.typography.titleMedium)
            Text(quiz.description ?: "Sem descrição", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { onEdit(quiz) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray, // Botão também cinza
                        contentColor = Color.White
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Editar")
                }

                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    onClick = { quiz.id?.let { onDelete(it) } },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = Color.White
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Excluir")
                }
            }
        }
    }
}
