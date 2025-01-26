package com.example.cardiosurgeryillustrator.ui.components.admin.admin_modules


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.models.mock.mockModules
import com.example.cardiosurgeryillustrator.models.student.module.Module

@Composable
fun AdminModuleCardList(
    modifier: Modifier = Modifier,
    modulesList: List<Module>,
    onModuleClick: (Module) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        LazyColumn(
            modifier = Modifier.padding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = modulesList, key = { it.id }) { module ->
                AdminModuleCard(
                    module = module,
                    onClick = { onModuleClick(module) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun AdminModuleCardListPreview() {
    AdminModuleCardList(
        modulesList = mockModules,
        onModuleClick = {}
    )
}