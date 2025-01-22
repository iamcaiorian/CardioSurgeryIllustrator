package com.example.cardiosurgeryillustrator.ui.components.student.modules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import com.example.cardiosurgeryillustrator.models.Subject
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc100
import com.example.cardiosurgeryillustrator.ui.theme.Zinc800

@Composable
fun SubjectCard(modifier: Modifier = Modifier, subject: Subject, onClick: (Subject) -> Unit) {
    Card (
        modifier = modifier,
        onClick = { onClick(subject) },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ){
        Column (
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
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

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                color = Zinc100
            )
        }
    }
}

@Preview
@Composable
private fun SubjectCardPreview() {
    SubjectCard(
        modifier = Modifier.fillMaxWidth(),
        subject = Subject(
            id = "3",
            title = "Assunto 1",
            description = "Description duis aute irure dolor in reprehenderit in voluptate velit."
        ),
        onClick = {}
    )
}