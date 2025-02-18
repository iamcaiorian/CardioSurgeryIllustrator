package com.example.cardiosurgeryillustrator.ui.components.patient.community.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CommunityCategoryFilterChipList(
    modifier: Modifier = Modifier,
    onSelectedCategoryChanged: (CommunityFilterChipView) -> Unit
) {
    val categories = listOf(
        CommunityFilterChipView.POPULARES,
        CommunityFilterChipView.SALVOS
    )
    var selectedCategory by remember { mutableStateOf(categories.first()) }

    LaunchedEffect(key1 = selectedCategory) {
        onSelectedCategoryChanged(selectedCategory)
    }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
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
