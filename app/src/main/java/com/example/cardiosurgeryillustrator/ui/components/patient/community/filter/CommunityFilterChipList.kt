package com.example.cardiosurgeryillustrator.ui.components.patient.community.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CommunityCategoryFilterChipList(
    modifier: Modifier = Modifier,
    onSelectedCategoryChanged: (CommunityFilterChipView) -> Unit
) {
    val categories = CommunityFilterChipView.values().toList()
    var selectedCategory by remember { mutableStateOf(categories.first()) }

    LaunchedEffect(key1 = selectedCategory) {
        onSelectedCategoryChanged(selectedCategory)
    }

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = categories) { category ->
            CommunityCategoryFilterChip(
                category = category,
                isSelected = category == selectedCategory,
                onClick = { isSelected ->
                    if (isSelected) selectedCategory = category
                }
            )
        }
    }
}

@Preview
@Composable
fun CommunityCategoryFilterChipListPreview() {
    CommunityCategoryFilterChipList(
        modifier = Modifier.fillMaxWidth(),
        onSelectedCategoryChanged = { /* Ação ao selecionar */ }
    )
}
