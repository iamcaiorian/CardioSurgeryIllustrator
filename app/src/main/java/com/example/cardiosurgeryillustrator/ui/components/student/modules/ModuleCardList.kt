package com.example.cardiosurgeryillustrator.ui.components.student.modules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.models.student.module.Module
import com.example.cardiosurgeryillustrator.models.mock.student.mockModules
import com.example.cardiosurgeryillustrator.ui.components.input.SearchInput

@Composable
fun ModuleCardList(
    modifier: Modifier = Modifier,
    modulesList: List<Module>,
    onModuleClick: (Module) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val filteredModules = modulesList.filter { module ->
        module.title.contains(query, ignoreCase = true) ||
                module.description.contains(query, ignoreCase = true)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Box {
            SearchInput(query = query, onQueryChange = { query = it })
        }

        LazyColumn(
            modifier = Modifier.padding(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = filteredModules, key = { it.id }) { module ->
                AnimatedModuleCard(
                    module = module,
                    onClick = { onModuleClick(module) }
                )
            }
        }
    }
}


@Preview
@Composable
private fun ModuleCardListPreview() {
    ModuleCardList(
        modulesList = mockModules,
        onModuleClick = {}
    )
}