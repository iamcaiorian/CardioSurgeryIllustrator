package com.example.cardiosurgeryillustrator.ui.components.faq

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.screens.admin.faq.FaqItem

@Composable
fun FaqItemRow(
    faq: FaqItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = faq.question, style = MaterialTheme.typography.titleMedium)
            Text(text = faq.answer, style = MaterialTheme.typography.bodyMedium)
        }
        Row (

        ) {
            StandardButton (
                onClick = onEditClick,
                iconRes = R.drawable.ic_edit
            )
            StandardButton (
                onClick = onDeleteClick,
                iconRes = R.drawable.ic_delete
            )
        }
    }
}