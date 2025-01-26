package com.example.cardiosurgeryillustrator.ui.screens.admin.create_module

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.models.student.module.Module
import com.example.cardiosurgeryillustrator.models.student.module.ModuleCreateRequest
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.components.input.StandardTextArea
import com.example.cardiosurgeryillustrator.ui.components.input.StandardTextField
import com.example.cardiosurgeryillustrator.ui.components.topBar.StandardTopBar
import com.example.cardiosurgeryillustrator.ui.view_models.admin.create_module.CreateModuleViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateModuleScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateModuleViewModel = viewModel(),
    onNavigateBack: () -> Unit,
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var cover by remember { mutableStateOf("") }
    var longDescription by remember { mutableStateOf("") }

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


                StandardTextArea(
                    value = longDescription,
                    label = "Descrição Longa",
                    onValueChange = { longDescription = it }
                )

                StandardButton(text = "Salvar", modifier = Modifier.fillMaxWidth(), onClick = {
                    val createModule = ModuleCreateRequest(
                        subjectId = "",
                        title = title,
                        description = description,
                        cover = cover,
                        progress = 0f,
                        longDescription = longDescription
                    )
                    viewModel.createModule(createModule)
                })

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateModuleScreenPreview() {
    CreateModuleScreen(
        onNavigateBack = {  },
    )
}

