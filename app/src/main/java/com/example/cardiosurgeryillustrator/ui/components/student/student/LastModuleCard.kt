package com.example.cardiosurgeryillustrator.ui.components.student.student

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.Module
import com.example.cardiosurgeryillustrator.models.mock.mockModules
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc300
import com.example.cardiosurgeryillustrator.ui.theme.Zinc700

@Composable
fun LastModuleCard(modifier: Modifier = Modifier, module: Module, onClick: (Module) -> Unit) {
    val percentageProgress = (module.progress * 100).toInt()

    Card(
        modifier = modifier,
        onClick = { onClick(module) },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )

    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            Arrangement.spacedBy(6.dp)
        ){
            Text("Últimos Estudos", style = Typography.headlineMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.module_anatomia_coracao),
                    modifier = Modifier
                        .height(120.dp)
                        .width(120.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentDescription = "Imagem do Local",
                    contentScale = ContentScale.Crop,

                    )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    Arrangement.spacedBy(20.dp)
                ) {

                    Text(text = module.title, style = Typography.headlineLarge)

                    Column {
                        Text(text = "$percentageProgress% concluído", color = Zinc700)

                        Spacer(modifier = Modifier.height(4.dp))

                        LinearProgressIndicator(
                            progress = { module.progress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            color = Blue700,
                            trackColor = Zinc300
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Continuar", color = Zinc700)

                        Spacer(modifier = Modifier.width(4.dp))

                        Icon(
                            modifier = Modifier.size(12.dp),
                            painter = painterResource(R.drawable.ic_play_arrow),
                            contentDescription = "Ícone de continuar",
                        )
                    }


                }
            }
        }


    }
}

@Preview
@Composable
private fun LastModuleCardPreview() {
    LastModuleCard(module = mockModules[0], onClick = {})
}