package com.example.cardiosurgeryillustrator.ui.screens.patient.faq

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.models.mock.mockFAQ
import com.example.cardiosurgeryillustrator.ui.components.faq.FaqDisplayItem
import com.example.cardiosurgeryillustrator.ui.components.input.SearchInput
import com.example.cardiosurgeryillustrator.ui.screens.admin.faq.FaqItem

@Composable
fun PatientFAQScreen(
    modifier: Modifier= Modifier
) {
    var faqList by remember { mutableStateOf(mockFAQ.map { FaqItem(it.id.toInt(), it.question, it.answer) }) }
    var query by remember { mutableStateOf("") }
    val filteredFAQ = faqList.filter { faq ->
        faq.question.contains(query, ignoreCase = true)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SearchInput(query = query, onQueryChange = { query = it })

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(items = filteredFAQ, key = { _, item -> item.id }) { index, faq ->
                FaqDisplayItem(
                    question = faq.question,
                    answer = faq.answer
                )
                if (index < filteredFAQ.lastIndex) {
                    Divider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPatientFAQScreen() {
    Surface {
        PatientFAQScreen()
    }
}
