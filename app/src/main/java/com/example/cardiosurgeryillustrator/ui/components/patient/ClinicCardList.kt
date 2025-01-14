package com.example.cardiosurgeryillustrator.ui.components.patient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.models.Clinic
import com.example.cardiosurgeryillustrator.models.mock.mockClinics
import com.example.cardiosurgeryillustrator.ui.theme.Typography

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