package com.example.cardiosurgeryillustrator.ui.components.patient.nearby_clinics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.models.patient.nearby_clinics.Clinic
import com.example.cardiosurgeryillustrator.models.mock.patient.mockClinics

@Composable
fun ClinicCardList(
    modifier: Modifier = Modifier,
    clinics: List<Clinic>,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = clinics, key = { it.id }) { clinic ->
            ClinicCard(
                clinic = clinic
            )
        }
    }
}


@Preview
@Composable
private fun ClinicCardListPreview() {
    ClinicCardList(
        clinics = mockClinics

    )
}