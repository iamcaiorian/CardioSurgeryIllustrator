package com.example.cardiosurgeryillustrator.ui.components.admin.admin_modules


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc200
import com.example.cardiosurgeryillustrator.ui.theme.Zinc300
import com.example.cardiosurgeryillustrator.ui.theme.Zinc900

@Composable
fun DeleteModuleButton(modifier: Modifier = Modifier) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            contentColor = Zinc900,
            containerColor = Zinc200
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Zinc900)
    ) {
        Text(text = "EXCLUIR", style = Typography.labelMedium)

    }
}

@Preview
@Composable
private fun DeleteModuleButtonPreview() {
    DeleteModuleButton()

}