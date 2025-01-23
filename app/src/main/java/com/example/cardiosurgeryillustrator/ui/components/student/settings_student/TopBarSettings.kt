package com.example.cardiosurgeryillustrator.ui.components.student.settings_student

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
fun TopBarSettings(modifier: Modifier = Modifier, onNavigateBack: () -> Unit, title: String) {
    TopAppBar(
        title = {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Button(
                    onClick = onNavigateBack,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Zinc900
                    )
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = "Ícone do botão",
                    )
                }
                Text(text = title, style = Typography.headlineLarge)
            }
        }
    )
}

@Preview
@Composable
@ExperimentalMaterial3Api
private fun TopBarSettingsPreview() {
    TopBarSettings(modifier = Modifier, onNavigateBack = {}, title = "Configurações")
}