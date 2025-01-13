package com.example.cardiosurgeryillustrator.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.button.ConfirmationButton
import com.example.cardiosurgeryillustrator.ui.components.buttons.TransparentButton
import com.example.cardiosurgeryillustrator.ui.components.patient.form.TextInputField
import com.example.cardiosurgeryillustrator.ui.components.settings.TopBarSettings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(modifier: Modifier = Modifier, onNavigateBack: () -> Unit) {
    Scaffold(topBar = {
        TopBarSettings(
            modifier,
            onNavigateBack,
            title = "Perfil"
        )
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar_1),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .clip(CircleShape)
                            .fillMaxWidth(0.3f)
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(top = 160.dp)
                    ) {
                        TransparentButton(
                            text = "Alterar avatar",
                            onClick = {},

                        )
                    }
                }


                TextInputField(
                    label = "Nome",
                    value = "Nome",
                    onValueChange = {},
                    modifier = Modifier.padding(vertical = 12.dp)
                )

                TextInputField(
                    label = "E-mail",
                    value = "fulano@gmail.com",
                    onValueChange = {},
                    modifier = Modifier.padding(vertical = 12.dp)
                )

                TextInputField(
                    label = "Senha",
                    value = "senha",
                    onValueChange = {},
                    isPassword = true,
                    modifier = Modifier.padding(vertical = 12.dp)

                )

                ConfirmationButton(
                    text = "Confirmar alteração",
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(onNavigateBack = {})
}