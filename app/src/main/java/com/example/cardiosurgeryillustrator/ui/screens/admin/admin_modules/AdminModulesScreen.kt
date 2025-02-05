package com.example.cardiosurgeryillustrator.ui.screens.admin.admin_modules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.repository.student.subject.SubjectRepository
import com.example.cardiosurgeryillustrator.ui.components.admin.admin_modules.AdminModuleCard
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.view_models.admin.admin_modules.AdminModulesViewModel
import com.example.cardiosurgeryillustrator.view_models.admin.admin_modules.AdminModulesViewModelFactory
import com.example.cardiosurgeryillustrator.utils.makeModuleEntityUtil

@Composable
fun AdminModulesScreen(
    modifier: Modifier = Modifier,
    onNavigateToCreateModule: () -> Unit
) {
    val subjectRepository = SubjectRepository()
    val viewModel: AdminModulesViewModel = viewModel(
        factory = AdminModulesViewModelFactory(subjectRepository)
    )
    var isLoading by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        isLoading = true
        viewModel.getAllSubjects()
        isLoading = false
    }

    val subjects = viewModel.subjects

    Box(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxSize(),

    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Column (
                modifier = Modifier,
                Arrangement.spacedBy(16.dp)
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    StandardButton(
                        onClick = onNavigateToCreateModule,
                        iconRes = R.drawable.ic_plus,
                        text = "Adicionar módulo"
                    )
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(bottom = 12.dp),
                ) {
                    subjects.forEach { subject ->

                        item {
                            Text(
                                text = subject.title,
                                style = Typography.headlineLarge,
                            )
                        }

                        items(items = subject.modules, key = { it.id }) { module ->
                            AdminModuleCard(
                                module = makeModuleEntityUtil(module),
                                onEditClick = { },
                                onDeleteClick = { },
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }


        }

    }
}


@Preview
@Composable
private fun ModulesScreenPreview() {
    AdminModulesScreen(onNavigateToCreateModule = {})
}