package com.example.cardiosurgeryillustrator.ui.components.patient.community

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Blue900
import com.example.cardiosurgeryillustrator.ui.theme.Typography

@Composable
fun SearchInputCommunity(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    TextField(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        value = text,
        onValueChange = { text = it },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Blue700.copy(alpha = 0.2f)
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon",
                modifier = Modifier.padding(4.dp).size(12.dp),
                tint = Blue900
            )
        },
        placeholder = { Text("Pesquisar", style = Typography.bodyMedium, color = Blue900)  },
    )
}

@Preview
@Composable
private fun SearchInputPreview() {
    SearchInputCommunity()
}