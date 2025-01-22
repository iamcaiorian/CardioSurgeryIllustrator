package com.example.cardiosurgeryillustrator.ui.components.student.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.ui.components.student.student.CircularProgressWithPercentage
import com.example.cardiosurgeryillustrator.ui.theme.Typography

@Composable
fun StudentQuizzes(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier.fillMaxWidth(),
        Arrangement.spacedBy(8.dp)
    ){
        Text(text = "Meus Quizzes", style = Typography.headlineLarge)

        Row (
            modifier = Modifier.fillMaxWidth(),
            Arrangement.spacedBy(16.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressWithPercentage(Modifier, 0.5f)

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Quiz 1",
                    style = Typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressWithPercentage(Modifier, 0f)

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Quiz 2",
                    style = Typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
private fun StudentQuizzesPreview() {
    StudentQuizzes()
}