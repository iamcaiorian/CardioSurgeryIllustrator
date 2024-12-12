package com.example.cardiosurgeryillustrator.ui.components.modules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.Subject
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc800

@Composable
fun SubjectCard(modifier: Modifier = Modifier, subject: Subject, onClick: (Subject) -> Unit) {
    Column (
        modifier = modifier,
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Text(text = subject.title, style = Typography.headlineLarge)
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "Ícone flecha para direita",

            )
        }
        Text(text = subject.description, style = Typography.bodyMedium, color = Zinc800)
    }
}

@Preview
@Composable
private fun SubjectCardPreview() {
    SubjectCard(
        modifier = Modifier.fillMaxWidth(),
        subject = Subject(
            id = "dadadad",
            title = "Assunto 1",
            description = "Description duis aute irure dolor in reprehenderit in voluptate velit."
        ),
        onClick = {}
    )
}