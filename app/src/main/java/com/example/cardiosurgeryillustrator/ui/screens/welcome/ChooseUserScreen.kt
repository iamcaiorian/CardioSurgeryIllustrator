package com.example.cardiosurgeryillustrator.ui.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.components.welcome.WelcomeHeader
import com.example.cardiosurgeryillustrator.ui.theme.Typography

@Composable
fun ChooseUserScreen(modifier: Modifier = Modifier, onNavigateToUser: () -> Unit) {
    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 96.dp)
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(80.dp, Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeHeader()

        Text(
            text =  "Explore o passo a passo de cirurgias " +
                    "cardiovasculares com ilustrações. Selecione " +
                    "abaixo se você é estudante ou paciente para começar.",
            style = Typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StandardButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Estudante",
                onClick = onNavigateToUser
            )

            Text(text = "ou")

            StandardButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Paciente",
                onClick = onNavigateToUser
            )
        }
    }
}

@Preview
@Composable
private fun ChooseUserScreenPreview() {
    ChooseUserScreen(onNavigateToUser = {})
}