package com.example.cardiosurgeryillustrator.ui.screens.patient.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.mock.patient.mockLifeStyleText
import com.example.cardiosurgeryillustrator.models.patient.home.LifeStyleText

@Composable
fun LifeStyleScreen(modifier: Modifier, imc: Float, navController: NavController) {
    val lifestyleAdvice = getLifestyleAdvice(imc)

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                text = "Estilo de vida",
                fontSize = 22.sp,
                color = Color.Black,
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            lifestyleAdvice?.let {

                item {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Explicação sobre seu IMC (${imc})",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(16.dp)
                        )
                        Text(
                            text = it.explanation,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Dicas de Alimentação",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(16.dp)
                        )
                        it.food.forEach { foodTip ->
                            Text(
                                text = foodTip,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }


                item {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Dicas de Estilo de Vida",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(16.dp)
                        )
                        it.lifeStyle.forEach { lifeStyleTip ->
                            Text(
                                text = lifeStyleTip,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            } ?: run {
                item {
                    Text(
                        "Nenhuma recomendação encontrada para o IMC informado.",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

// Função para buscar as recomendações com base no IMC
fun getLifestyleAdvice(imc: Float): LifeStyleText? {
    return mockLifeStyleText.find { imc <= it.imc }
}

@Preview(showBackground = true)
@Composable
private fun LifeStyleScreenPreview() {
    LifeStyleScreen(modifier = Modifier, imc = 26.0f, navController = rememberNavController())
}
