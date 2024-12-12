package com.example.cardiosurgeryillustrator.ui.components.modules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.models.Subject
import com.example.cardiosurgeryillustrator.models.mock.mockSubjects
import com.example.cardiosurgeryillustrator.ui.components.input.SearchInput
import com.example.cardiosurgeryillustrator.ui.theme.Zinc100
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun SubjectCardList(
    modifier: Modifier = Modifier,
    subjectList: List<Subject>,
    onSubjectClick: (Subject) -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        Arrangement.spacedBy(24.dp)
    ) {

        Box {
            SearchInput()
        }

        LazyColumn {
            items(items = subjectList, key = { it.id }) { subject ->
                SubjectCard(subject = subject, onClick = {
                    onSubjectClick(subject)
                })

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    color = Zinc100
                )
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