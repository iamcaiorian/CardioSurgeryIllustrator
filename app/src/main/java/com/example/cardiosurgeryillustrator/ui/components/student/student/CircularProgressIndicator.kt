package com.example.cardiosurgeryillustrator.ui.components.student.student

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc300

@Composable
fun CircularProgressWithPercentage(modifier: Modifier = Modifier, progress: Float) {
    val percentageProgress = (progress * 100).toInt()

    Box(contentAlignment = Alignment.Center, modifier = modifier.size(96.dp)) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            progress = { progress },
            color = Blue700,
            trackColor = Zinc300,
            strokeWidth = 4.dp
        )
        Text(
            text = "$percentageProgress%",
            color = if (progress == 0f) Zinc300 else Blue700,
            style = Typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview
fun PreviewCircularProgress() {
    CircularProgressWithPercentage(progress = 0.58f)
}