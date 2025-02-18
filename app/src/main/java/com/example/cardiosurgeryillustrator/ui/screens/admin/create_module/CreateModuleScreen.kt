package com.example.cardiosurgeryillustrator.ui.screens.admin.create_module

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.example.cardiosurgeryillustrator.models.patient.nearby_clinics.Clinic
import com.example.cardiosurgeryillustrator.models.student.module.Module
import com.example.cardiosurgeryillustrator.models.student.module.ModuleCreateRequest
import com.example.cardiosurgeryillustrator.models.student.subject.Subject
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository
import com.example.cardiosurgeryillustrator.repository.student.subject.SubjectRepository
import com.example.cardiosurgeryillustrator.ui.components.admin.create_module.SubjectDropdown
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.components.input.StandardTextArea
import com.example.cardiosurgeryillustrator.ui.components.input.StandardTextField
import com.example.cardiosurgeryillustrator.ui.components.patient.appointment_schedule.ClinicDropdown
import com.example.cardiosurgeryillustrator.ui.components.topBar.StandardTopBar
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.view_models.admin.create_module.CreateModuleViewModel
import com.example.cardiosurgeryillustrator.view_models.admin.create_module.CreateModuleViewModelFactory
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateModuleScreen(
    modifier: Modifier = Modifier,
    moduleRepository: ModuleRepository = ModuleRepository(),
    subjectRepository: SubjectRepository = SubjectRepository(),
    onNavigateBack: () -> Unit,
    onNavigateToModules: () -> Unit,
) {
    val viewModel: CreateModuleViewModel = viewModel(
        factory = CreateModuleViewModelFactory(moduleRepository, subjectRepository)
    )

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var cover by remember { mutableStateOf("") }
    var longDescription by remember { mutableStateOf("") }
    var selectedSubject by remember { mutableStateOf<Subject?>(null) }

    LaunchedEffect(Unit) {
        viewModel.getAllSubjects()
    }

    Scaffold(
        topBar = {
            StandardTopBar(
                modifier,
                onNavigateBack,
                title = "Criar Módulo"
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Title field
                StandardTextField(
                    value = title,
                    label = "Título",
                    onValueChange = { title = it }
                )

                // Description field
                StandardTextField(
                    value = description,
                    label = "Descrição",
                    onValueChange = { description = it }
                )

                // Cover field
                StandardTextField(
                    value = cover,
                    label = "Capa (URL)",
                    onValueChange = { cover = it }
                )

                // Long Description field
                StandardTextArea(
                    value = longDescription,
                    label = "Descrição Longa",
                    onValueChange = { longDescription = it }
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopStart
                ) {
                    Column {
                        Text("Escolher Assunto", style = Typography.headlineMedium)
                        SubjectDropdown(
                            subjects = viewModel.subjects,
                            selectedSubject = selectedSubject,
                            onSubjectSelected = { selectedSubject = it }
                        )
                    }
                }



                // Save Button
                StandardButton(
                    text = "Salvar",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (selectedSubject != null) {
                            val createModule = ModuleCreateRequest(
                                subjectId = selectedSubject!!.id,
                                title = title,
                                description = description,
                                cover = cover,
                                progress = 0f,
                                longDescription = longDescription,
                                isFavorite = false,
                            )
                            viewModel.createModule(
                                moduleCreateRequest = createModule,
                                onNavigateToModules = onNavigateToModules
                            )
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateModuleScreenPreview() {
    CreateModuleScreen(
        onNavigateBack = { },
        onNavigateToModules = { }
    )
}


