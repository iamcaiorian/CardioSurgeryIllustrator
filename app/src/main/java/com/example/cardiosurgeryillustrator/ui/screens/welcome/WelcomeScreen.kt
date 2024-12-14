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
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.components.welcome.WelcomeHeader
import com.example.cardiosurgeryillustrator.ui.theme.Typography

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, onNavigateToChooseUser: () -> Unit) {
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
            text =  "Este aplicativo foi criado para pacientes e " +
                    "estudantes de medicina que desejam entender melhor " +
                    "os procedimentos cardiovasculares. Aqui, você encontrará " +
                    "ilustrações detalhadas de cirurgias cardíacas, " +
                    "explicando cada etapa de forma acessível e visual.\n" +
                    "Explore e aprenda mais sobre o coração!",
            style = Typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        StandardButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Começar",
            onClick = onNavigateToChooseUser
        )
    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(onNavigateToChooseUser = {})
}