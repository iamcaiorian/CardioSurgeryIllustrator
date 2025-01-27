package com.example.cardiosurgeryillustrator.ui.components.patient.community.forum

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc100
import com.example.cardiosurgeryillustrator.ui.theme.Zinc300

@Composable
fun ForumTopBar(
    title: String,
    subtitle: String,
    backgroundImageRes: Int,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            Image(
                painter = painterResource(id = backgroundImageRes),
                contentDescription = "Imagem de Fundo do Fórum",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.7f),
                                Color.Transparent
                            ),
                            startY = Float.POSITIVE_INFINITY,
                            endY = 0f
                        )
                    )
            )


            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                StandardButton(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape),
                    iconRes = R.drawable.ic_arrow_left,
                    onClick = {}
                )

                Column {
                    Text(
                        text = subtitle,
                        style = Typography.headlineLarge,
                        color = Zinc100
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = title,
                        style = Typography.bodyLarge,
                        color = Zinc300
                    )
                }
            }
        }

        Column(modifier = Modifier.fillMaxSize()) {
            repeat(1) {
                Row(
                    modifier = Modifier
                        .background(color = Blue700)
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StandardButton(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape),
                        iconRes = R.drawable.ic_arrow_left,
                        onClick = {}
                    )

                    Text(
                        text = subtitle,
                        style = Typography.headlineMedium,
                        color = Zinc100
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ForumTopBarPreview() {
    ForumTopBar(
        title = "Pós Operatório",
        subtitle = "Como foi seu pós operatório?",
        backgroundImageRes = R.drawable.img_defaul
    )
}