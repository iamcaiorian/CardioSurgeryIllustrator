package com.example.cardiosurgeryillustrator.ui.screens.admin.faq

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.models.mock.mockFAQ
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.components.faq.FaqForm
import com.example.cardiosurgeryillustrator.ui.components.faq.FaqItemRow

@Composable
fun FAQScreen(
    onAddFaq: (String, String) -> Unit,
    onEditFaq: (FaqItem) -> Unit,
    onDeleteFaq: (FaqItem) -> Unit
) {
    var faqList by remember { mutableStateOf(mockFAQ.map { FaqItem(it.id.toInt(), it.question, it.answer) }) }
    var showForm by remember { mutableStateOf(false) }
    var selectedFaq by remember { mutableStateOf<FaqItem?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Gerenciar FAQ",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(faqList) { faq ->
                FaqItemRow(
                    faq = faq,
                    onEditClick = {
                        selectedFaq = faq
                        showForm = true
                    },
                    onDeleteClick = {
                        faqList = faqList.filter { it.id != faq.id }
                        onDeleteFaq(faq)
                    }
                )
            }
        }

        StandardButton (
            onClick = {
                selectedFaq = null
                showForm = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            text = "Adicionar FAQ"
        )
    }

    if (showForm) {
        FaqForm(
            faq = selectedFaq,
            onSave = { question, answer ->
                if (selectedFaq == null) {
                    val newId = (faqList.maxOfOrNull { it.id } ?: 0) + 1
                    faqList = faqList + FaqItem(newId, question, answer)
                    onAddFaq(question, answer)
                } else {
                    faqList = faqList.map {
                        if (it.id == selectedFaq!!.id) it.copy(question = question, answer = answer) else it
                    }
                    onEditFaq(selectedFaq!!.copy(question = question, answer = answer))
                }
                showForm = false
            },
            onCancel = { showForm = false }
        )
    }
}

data class FaqItem(val id: Int, val question: String, val answer: String)

@Preview
@Composable
fun PreviewFAQScreen() {
    FAQScreen(
        onAddFaq = { question, answer -> },
        onEditFaq = { },
        onDeleteFaq = { }
    )
}

