package com.example.cardiosurgeryillustrator.ui.components.admin.admin_modules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.models.mock.mockModules
import com.example.cardiosurgeryillustrator.models.student.module.Module
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc100

@Composable
fun AdminModuleCard(modifier: Modifier = Modifier, module: Module, onClick: (Module) -> Unit) {
    Card(
        modifier = modifier,
        onClick = { onClick(module) },
        colors = CardDefaults.cardColors(
            containerColor = Zinc100
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = module.title,
                    style = Typography.headlineLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )


                Text(
                    text = module.description,
                    style = Typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row (
                    modifier =Modifier,
                    Arrangement.spacedBy(8.dp)
                ){
                    UpdateModuleButton()
                    DeleteModuleButton()
                }
            }
        }
    }

}

@Preview
@Composable
private fun AdminModuleCardPreview() {
    AdminModuleCard(modifier = Modifier.fillMaxWidth(), module = mockModules[0], onClick = {})
}