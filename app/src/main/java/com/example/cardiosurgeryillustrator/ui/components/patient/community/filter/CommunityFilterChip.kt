package com.example.cardiosurgeryillustrator.ui.components.patient.community.filter

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.ui.theme.Blue600
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc400

@Composable
fun CommunityCategoryFilterChip(
    modifier: Modifier = Modifier,
    category: CommunityFilterChipView,
    isSelected: Boolean,
    onClick: (Boolean) -> Unit
) {
    FilterChip(
        modifier = modifier
            .padding(2.dp)
            .heightIn(min = 36.dp),
        leadingIcon = if (isSelected) {
            {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = category.icon),
                    tint = Color.White,
                    contentDescription = "Ícone de Filtro de Categoria"
                )
            }
        } else null,
        border = FilterChipDefaults.filterChipBorder(
            enabled = false,
            selected = isSelected,
            disabledBorderColor = Zinc400,
            borderWidth = 1.dp,
            selectedBorderWidth = 0.dp,
            selectedBorderColor = Color.Transparent
        ),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.White,
            selectedContainerColor = Blue600,
        ),
        selected = isSelected,
        onClick = { onClick(!isSelected) },
        label = {
            Text(
                text = category.description,
                style = Typography.bodyMedium,
                color = if (isSelected) Color.White else Zinc400
            )
        }
    )
}
