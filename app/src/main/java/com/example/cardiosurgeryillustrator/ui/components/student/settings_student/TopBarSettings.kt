package com.example.cardiosurgeryillustrator.ui.components.student.settings_student

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc900

@Composable
@ExperimentalMaterial3Api
fun TopBarSettings(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    title: String
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = "Voltar",
                        tint = Zinc900
                    )
                }
                Text(
                    text = title,
                    style = Typography.headlineLarge,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    )
}

@Preview
@Composable
@ExperimentalMaterial3Api
private fun TopBarSettingsPreview() {
    TopBarSettings(
        modifier = Modifier,
        onNavigateBack = {},
        title = "Configurações"
    )
}
