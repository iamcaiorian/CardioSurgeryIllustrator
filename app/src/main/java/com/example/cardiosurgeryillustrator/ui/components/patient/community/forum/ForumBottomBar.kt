package com.example.cardiosurgeryillustrator.ui.components.patient.community.forum

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.components.input.TextInput

@Composable
fun ForumBottomBar() {
    Row(
        modifier = Modifier
            .background(color = Color.White)
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextInput(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.width(12.dp))

        StandardButton(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            iconRes = R.drawable.ic_send,
            onClick = {}
        )
    }
}