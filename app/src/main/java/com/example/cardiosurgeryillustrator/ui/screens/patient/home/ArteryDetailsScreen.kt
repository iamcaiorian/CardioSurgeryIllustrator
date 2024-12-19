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

@ExperimentalMaterial3Api
@Composable
fun ArteryDetailsScreen(navController: NavController, arteryName: String, modifier: Modifier) {
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
                text = "Artéria ${arteryName} entupida",
                fontSize = 22.sp,
                color = Color.Black,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.artery_blocked),
                contentDescription = "Imagem da Artéria",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Artéria entupida",
                fontSize = 22.sp,

                )

            Spacer(modifier = Modifier.height(8.dp))

            // Parágrafo de texto
            Text(
                text = "Acúmulo de Placas de Gordura\n\nO principal responsável pelo entupimento das artérias é o depósito de placas de gordura (lipídios), principalmente colesterol LDL" +
                        "(\"colesterol ruim\"). Quando esses depósitos se acumulam na parede arterial, eles formam placas que estreitam o diâmetro interno da artéria, dificultando o fluxo sanguíneo.",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Seção adicional (Exemplo: Fatores de risco)
            Text(
                text = "Fatores de risco",
                fontSize = 22.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Dieta rica em gorduras saturadas e trans aumenta os níveis de colesterol LDL," +
                        " sedentarismo diminui a capacidade do corpo de metabolizar gorduras, " +
                        "tabagismo danifica a parede arterial e aumenta o risco de inflamação, " +
                        "altos níveis de glicose no sangue favorecem o acúmulo de gordura nas artérias, " +
                        "pressão alta força as paredes das artérias, tornando-as mais vulneráveis a lesões, " +
                        "e obesidade está associada a níveis elevados de colesterol e pressão arterial.",
                fontSize = 16.sp
            )
        }
    }
}


@Preview
@ExperimentalMaterial3Api
@Composable
private fun ArteryDetailsScreenPreview() {
    ArteryDetailsScreen(navController = rememberNavController(), arteryName = "Coronária", modifier = Modifier)
}