package com.example.cardiosurgeryillustrator.ui.screens.patient.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.mock.patient.diseases
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.modals.feedback.FeedbackModal

@Composable
fun HomePacientScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val selectedDisease = diseases[0]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val showBottomSheet = remember { mutableStateOf(false) }

        Row {
            Image(
                painter = painterResource(id = R.drawable.heart_icon),
                modifier = Modifier.size(24.dp),
                contentDescription = "Logo do aplicativo"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Cardio Surgery Illustrator",
                modifier = Modifier.padding(bottom = 8.dp),
                fontSize = 20.sp,
                color = Color(0xFF0369A1)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Meu coração",
            fontSize = 22.sp,
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            val heartImage: Painter = painterResource(id = R.drawable.heart_image)
            Image(
                painter = heartImage,
                contentDescription = "Heart illustration",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(300.dp)
            )

            selectedDisease.points.forEach { point ->
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .offset(x = point.xOffset, y = point.yOffset)
                        .clickable { navController.navigate(point.route) }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.point_icon), // Ícone do ponto
                        contentDescription = "Clickable artery",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        StandardButton(
            onClick = { showBottomSheet.value = true },
            text = "Abrir Feedback",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        StandardButton(
            onClick = { navController.navigate("life_Style") },
            text = "Ver Estilo de Vida",
            modifier = Modifier.fillMaxWidth()
        )

        if (showBottomSheet.value) {
            FeedbackModal(
                onDismiss = { showBottomSheet.value = false }
            )
        }
    }
}

@Preview
@Composable
private fun HomePacientScreenPreview() {
    HomePacientScreen(modifier = Modifier.fillMaxWidth(), navController = rememberNavController())
}