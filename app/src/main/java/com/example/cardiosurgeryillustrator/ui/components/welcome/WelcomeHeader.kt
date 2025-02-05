package com.example.cardiosurgeryillustrator.ui.components.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Typography

@Composable
fun WelcomeHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Seja bem-vindo ao", style = Typography.headlineMedium)
        Image(
            painter = painterResource(id = R.drawable.heart_icon),
            contentDescription = "CSI Logo",
            modifier = Modifier.size(100.dp)
        )
        Text(text = "Cardio Surgery Illustrator", style = Typography.headlineLarge, textAlign = TextAlign.Center, color = Blue700)
    }
}

@Preview
@Composable
private fun WelcomeHeaderPreview() {
    WelcomeHeader()
}