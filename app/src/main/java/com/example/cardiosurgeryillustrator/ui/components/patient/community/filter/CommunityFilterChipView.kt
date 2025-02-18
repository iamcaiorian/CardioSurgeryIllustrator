package com.example.cardiosurgeryillustrator.ui.components.patient.community.filter

import androidx.annotation.DrawableRes
import com.example.cardiosurgeryillustrator.R

enum class CommunityFilterChipView(
    val description: String,
    @DrawableRes val icon: Int
) {
    POPULARES(description = "Populares", icon = R.drawable.ic_selected),
    SALVOS(description = "Salvos", icon = R.drawable.ic_selected);

    companion object {
        fun fromDescription(description: String): CommunityFilterChipView? {
            return values().find { it.description == description }
        }
    }
}
