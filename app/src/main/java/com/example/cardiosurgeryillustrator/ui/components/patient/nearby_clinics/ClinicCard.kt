package com.example.cardiosurgeryillustrator.ui.components.patient.nearby_clinics

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.patient.nearby_clinics.Clinic
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc100
import com.example.cardiosurgeryillustrator.ui.theme.Zinc200
import com.example.cardiosurgeryillustrator.ui.theme.Zinc500

@Composable
fun ClinicCard(
    modifier: Modifier = Modifier,
    clinic: Clinic,
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Zinc100)
            .border(width = 1.dp, color = Zinc200, shape = RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Placeholder image
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxWidth(0.3f)
                    .aspectRatio(1f),
                painter = painterResource(R.drawable.ic_clinic_placeholder),
                contentDescription = "Imagem da clínica",
                contentScale = ContentScale.Crop
            )

            Column {
                Text(
                    text = clinic.name,
                    style = Typography.headlineSmall.copy(fontSize = 16.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = clinic.description?.takeIf { it.isNotBlank() }
                        ?: "Descrição não disponível",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Zinc500,
                    style = Typography.bodyLarge.copy(fontSize = 12.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Endereço: ${clinic.address?.takeIf { it.isNotBlank() } ?: "Endereço não informado"}",
                    color = Zinc500,
                    style = Typography.bodyMedium.copy(fontSize = 12.sp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Contato: ${clinic.phone?.takeIf { it.isNotBlank() } ?: "Contato não informado"}",
                    color = Zinc500,
                    style = Typography.bodyMedium.copy(fontSize = 12.sp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ClinicCardPreview() {
    ClinicCard(
        modifier = Modifier.fillMaxWidth(),
        clinic = Clinic(
            id = "1",
            name = "Clínica Vida Saúde",
            description = "Especializada em cardiologia e tratamentos avançados.",
            latitude = -23.550520,
            longitude = -46.633308,
            address = "Av. Saúde, 123 - Centro",
            phone = "(11) 98765-4321"
        )
    )
}