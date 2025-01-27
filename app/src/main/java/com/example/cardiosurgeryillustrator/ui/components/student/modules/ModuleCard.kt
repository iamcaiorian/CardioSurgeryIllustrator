package com.example.cardiosurgeryillustrator.ui.components.student.modules

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.student.module.Module
import com.example.cardiosurgeryillustrator.models.mock.mockModules
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc300

@Composable
fun ModuleCard(
    modifier: Modifier = Modifier,
    module: Module,
    onClick: (Module) -> Unit
) {

    Card(
        modifier = modifier,
        onClick = { onClick(module) },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
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
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = module.title,
                        style = Typography.headlineLarge,
                        modifier = Modifier.weight(1f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Icon(
                        imageVector = if (module.isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = animateColorAsState(
                            targetValue = if (module.isFavorite.value) Blue700 else Color.Gray
                        ).value,
                        modifier = Modifier
                            .size(
                                animateFloatAsState(
                                    targetValue = if (module.isFavorite.value) 28.dp.value else 24.dp.value
                                ).value.dp
                            )
                            .clickable {
                                module.isFavorite.value = !module.isFavorite.value
                            }
                    )
                }

                Text(
                    text = module.description,
                    style = Typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

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
        }
    }
}
@Composable
fun AnimatedModuleCard(
    modifier: Modifier = Modifier,
    module: Module,
    onClick: (Module) -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(durationMillis = 2000)),
        exit = fadeOut(animationSpec = tween(durationMillis = 2000))
    ) {
        ModuleCard(
            modifier = modifier,
            module = module,
            onClick = onClick
        )
    }
}

@Preview
@Composable
private fun ModuleCardPreview() {
    ModuleCard(modifier = Modifier.fillMaxWidth(), module = mockModules[0], onClick = {})
}