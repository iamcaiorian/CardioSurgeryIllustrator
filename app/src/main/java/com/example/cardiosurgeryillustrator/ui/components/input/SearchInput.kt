package com.example.cardiosurgeryillustrator.ui.components.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.cardiosurgeryillustrator.ui.theme.Blue100
import com.example.cardiosurgeryillustrator.ui.theme.Blue200
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchInput(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    TextField(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        value = text,
        onValueChange = { text = it },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Blue100
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon",
                modifier = Modifier.padding(4.dp).size(12.dp),
                tint = Blue700
            )
        },
        placeholder = { Text("Pesquisar", style = Typography.bodyMedium, color = Blue700)  },
        )
}

@Preview
@Composable
private fun SearchInputPreview() {
    SearchInput()
}