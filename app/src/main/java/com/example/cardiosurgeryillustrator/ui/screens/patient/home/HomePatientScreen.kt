package com.example.cardiosurgeryillustrator.ui.screens.patient.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun HomePacientScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    diseaseIndex: Int,
) {

    val selectedDisease = diseases[diseaseIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
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
                        .size(600.dp)
                )

                selectedDisease.points.forEach { point ->
                    Box(
                        modifier = Modifier
                            .size(32.dp)
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
        }

        StandardButton(
            onClick = { navController.navigate("life_Style") },
            text = "Ver Estilo de Vida",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
    }

}

@Preview
@Composable
private fun HomePacientScreenPreview() {
    HomePacientScreen(
        modifier = Modifier.fillMaxWidth(),
        navController = rememberNavController(),
        diseaseIndex = 0
    )
}