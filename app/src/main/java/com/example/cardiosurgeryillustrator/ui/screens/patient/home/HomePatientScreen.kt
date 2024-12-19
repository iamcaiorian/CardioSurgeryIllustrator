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

@Composable
fun HomePacientScreen(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
            color = Color.Black
        )

        // Heart Image with Clickable Points
        Box(modifier = Modifier.fillMaxWidth()) {
            val heartImage: Painter =
                painterResource(id = R.drawable.heart_image)
            Image(
                painter = heartImage,
                contentDescription = "Heart illustration",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(300.dp)
            )

            // Clickable Points
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .offset(x = 120.dp, y = 80.dp)
                    .clickable { navController.navigate("artery_details/Coronária") }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.point_icon), // Ícone do ponto
                    contentDescription = "Clickable artery",
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Ponto clicável 2
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .offset(x = 200.dp, y = 150.dp)
                    .clickable { navController.navigate("artery_details/Aorta") }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.point_icon),
                    contentDescription = "Clickable artery 2",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column {

            Text(
                text = "Estilo de vida",
                fontSize = 22.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(text = "Melhorias", fontSize = 16.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 8.dp))
            Text(
                text = "A adoção de um estilo de vida saudável é fundamental para a promoção da saúde" +
                        " e prevenção de doenças crônicas, como hipertensão, diabetes, obesidade e problemas" +
                        " cardiovasculares. Muitos fatores de risco estão diretamente associados a hábitos " +
                        "de vida inadequados, e pequenas mudanças na rotina podem trazer grandes benefícios" +
                        " à saúde a longo prazo.",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Risk Factors Section
            Text(
                text = "Fatores de risco",
                fontSize = 22.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(text = "Prevenção", fontSize = 16.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 8.dp))
            Text(
                text = "Existem fatores de risco que podem ser controlados por meio das melhorias no " +
                        "estilo de vida. Os principais incluem: Sedentarismo\n" +
                        "Alimentação inadequada\n" +
                        "Tabagismo\n" +
                        "Consumo excessivo de álcool\n" +
                        "Estresse crônico\n" +
                        "Obesidade\n" +
                        "Por outro lado, alguns fatores de risco não são modificáveis, como idade " +
                        "avançada, genética e histórico familiar. Contudo, adotar um estilo de vida" +
                        " saudável pode minimizar os impactos desses fatores e melhorar a qualidade de vida.",
                fontSize = 14.sp
            )
        }
    }
}


@Preview
@Composable
private fun HomePacientScreenPreview() {
    HomePacientScreen(modifier = Modifier.fillMaxWidth(), navController = rememberNavController())
}