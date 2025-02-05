package com.example.cardiosurgeryillustrator.ui.screens.admin.faq

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.mock.patient.mockFAQ
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.components.faq.FaqForm
import com.example.cardiosurgeryillustrator.ui.components.faq.FaqItemRow
import com.example.cardiosurgeryillustrator.ui.components.input.SearchInput

@Composable
fun FAQScreen(
    modifier: Modifier = Modifier
) {
    var faqList by remember { mutableStateOf(mockFAQ.map { FaqItem(it.id.toInt(), it.question, it.answer) }) }
    var showForm by remember { mutableStateOf(false) }
    var selectedFaq by remember { mutableStateOf<FaqItem?>(null) }
    var query by remember { mutableStateOf("") }
    val filteredFAQ = faqList.filter { faq ->
        faq.question.contains(query, ignoreCase = true)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment  = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SearchInput(query = query, onQueryChange = { query = it })

            StandardButton (
                onClick = {
                    selectedFaq = null
                    showForm = true
                },
                iconRes = R.drawable.ic_plus,
            )
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items = filteredFAQ, key = { it.id }) { faq ->
                FaqItemRow(
                    faq = faq,
                    onEditClick = {
                        selectedFaq = faq
                        showForm = true
                    },
                    onDeleteClick = {
                        faqList = faqList.filter { it.id != faq.id }
                    }
                )
                Divider()
            }
        }
    }

    if (showForm) {
        FaqForm(
            faq = selectedFaq,
            onSave = { question, answer ->
                if (selectedFaq == null) {
                    val newId = (faqList.maxOfOrNull { it.id } ?: 0) + 1
                    faqList = faqList + FaqItem(newId, question, answer)
                } else {
                    faqList = faqList.map {
                        if (it.id == selectedFaq!!.id) it.copy(question = question, answer = answer) else it
                    }
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
    FAQScreen()
}

