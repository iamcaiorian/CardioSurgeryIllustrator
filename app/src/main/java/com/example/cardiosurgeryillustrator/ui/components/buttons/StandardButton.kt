package com.example.cardiosurgeryillustrator.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Typography

@Composable
fun StandardButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    @DrawableRes iconRes: Int? = null,
    onClick: () -> Unit,
    color: Color = Blue700
) {
    val isIconOnly = text == null && iconRes != null
    Button(
        modifier = modifier
            .then(
                if (isIconOnly) Modifier.size(48.dp)
                else Modifier.heightIn(min = 48.dp)
            ),
        shape = RoundedCornerShape(8.dp),
        contentPadding = if (text == null && iconRes != null) PaddingValues(0.dp) else ButtonDefaults.ContentPadding,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            iconRes?.let {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    painter = painterResource(id = iconRes),
                    contentDescription = "Ícone do Botão",
                )
            }
            text?.let { Text(text = text.uppercase(), style = Typography.labelMedium) }
        }
    }
}

@Preview
@Composable
private fun StandardButtonPreview() {
    StandardButton(
        modifier = Modifier.fillMaxWidth(),
        text = "Confirmar",
        iconRes = R.drawable.ic_scan,
        color = Color.Green,
        onClick = {}
    )
}

@Preview
@Composable
private fun StandardButtonNoIconPreview() {
    StandardButton(
        modifier = Modifier.fillMaxWidth(),
        text = "Confirmar",
        color = Color.Red,
        onClick = {}
    )
}

@Preview
@Composable
private fun StandardButtonNoTextPreview() {
    StandardButton(
        modifier = Modifier,
        iconRes = R.drawable.ic_arrow_left,
        color = Blue700,
        onClick = {}
    )
}
