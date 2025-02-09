package com.example.cardiosurgeryillustrator.ui.components.patient.community.forum

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.patient.community.Topic
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc100
import com.example.cardiosurgeryillustrator.ui.theme.Zinc300

@Composable
fun ForumTopBar(
    topic: Topic,
    backgroundImageRes: Int,
    modifier: Modifier = Modifier,
    isTopicSaved: Boolean,
    onSaveToggle: (Boolean) -> Unit,
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp)
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
                modifier = Modifier
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
                    onClick = onBackClick
                )

                Column {
                    Text(
                        text = topic.title,
                        style = Typography.headlineLarge,
                        color = Zinc100
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = topic.theme,
                        style = Typography.bodyLarge,
                        color = Zinc300
                    )
                }
            }
        }

        ForumInteractions(
            topic = topic,
            isTopicSaved = isTopicSaved,
            onSaveToggle = { topicId, newState -> onSaveToggle(newState) }
        )
    }
}
