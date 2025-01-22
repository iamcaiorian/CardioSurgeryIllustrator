package com.example.cardiosurgeryillustrator.ui.components.student.modules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.models.Subject
import com.example.cardiosurgeryillustrator.models.mock.mockSubjects
import com.example.cardiosurgeryillustrator.ui.components.input.SearchInput
import com.example.cardiosurgeryillustrator.ui.theme.Zinc100

@Composable
fun SubjectCardList(
    modifier: Modifier = Modifier,
    subjectList: List<Subject>,
    onSubjectClick: (Subject) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val filteredSubjects = subjectList.filter {subject ->
        subject.title.contains(query, ignoreCase = true) ||
                subject.description.contains(query, ignoreCase = true)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Box {
            SearchInput(
                query = query,
                onQueryChange = { query = it }
            )
        }

        LazyColumn (
            modifier = Modifier.padding(),
        ){
            items(items = filteredSubjects, key = { it.id }) { subject ->
                SubjectCard(subject = subject, onClick = {
                    onSubjectClick(subject)
                })
            }
        }
    }
}


@Preview
@Composable
private fun SubjectCardListPreview() {
    SubjectCardList(
        modifier = Modifier.fillMaxWidth(),
        subjectList = mockSubjects,
        onSubjectClick = {}
    )
}