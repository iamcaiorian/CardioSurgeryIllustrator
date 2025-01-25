package com.example.cardiosurgeryillustrator.ui.screens.patient

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
import com.example.cardiosurgeryillustrator.ui.components.student.settings_student.SettingsOption

@Composable
fun MoreScreen(modifier: Modifier = Modifier, navController: NavController) {
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

        SettingsOption(
            onClickOption = { navController.navigate("nearby_clinics") },
            title = "Clínicas Próximas"
        )

        SettingsOption(
            onClickOption = { navController.navigate("appointment_schedule_screen") },
            title = "Registrar Consulta"
        )
    }
}


@Preview
@Composable
private fun MoreScreenPreview() {
    MoreScreen(modifier = Modifier.fillMaxWidth(), navController = rememberNavController())
}