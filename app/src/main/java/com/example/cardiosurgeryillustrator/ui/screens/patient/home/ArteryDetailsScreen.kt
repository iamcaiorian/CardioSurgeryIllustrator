package com.example.cardiosurgeryillustrator.ui.screens.patient.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.mock.mockArtery

@ExperimentalMaterial3Api
@Composable
fun ArteryDetailsScreen(navController: NavController, arteryName: String, modifier: Modifier) {

    val artery = mockArtery.find { it.id == arteryName }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo e Título do aplicativo
        Row {
            Image(
                painter = painterResource(id = R.drawable.heart_icon), // Ícone do coração
                modifier = Modifier.size(24.dp),
                contentDescription = "Logo do aplicativo"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Cardio Surgery Illustrator",
                fontSize = 20.sp,
                color = Color(0xFF0369A1)
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar"
                )
            }

            Text(
                text = "Artéria ${artery?.arteryName ?: "Desconhecida"}",
                fontSize = 22.sp,
                color = Color.Black,
            )
        }

        if (artery != null) {
            Image(
                painter = painterResource(id = artery.imageRes),
                contentDescription = "Imagem da Artéria ${artery.arteryName}",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                artery.texts.forEach { infoText ->
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = infoText.title,
                                fontSize = 22.sp,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = infoText.subtitle,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = infoText.content,
                                fontSize = 16.sp,
                                color = Color.Gray
                            )

                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(56.dp))
                    }
                }
            }
        } else {
            Text(
                text = "Artéria não encontrada.",
                fontSize = 18.sp,
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


@Preview
@ExperimentalMaterial3Api
@Composable
private fun ArteryDetailsScreenPreview() {
    ArteryDetailsScreen(
        navController = rememberNavController(),
        arteryName = "left_coronary",
        modifier = Modifier
    )
}