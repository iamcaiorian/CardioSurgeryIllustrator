package com.example.cardiosurgeryillustrator.ui.modals.feedback

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import kotlinx.coroutines.launch
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.OtherTexts
import com.example.cardiosurgeryillustrator.ui.theme.Zinc700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackModal(onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var currentStep by remember { mutableStateOf(0) }
    var answers by remember { mutableStateOf(mutableMapOf<String, String>()) }

    val questions = listOf(
        "Qual seu nome?",
        "Qual seu email?",
        "Qual seu país?",
        "Qual seu estado?",
        "Qual sua cidade?",
        "O que achou do nosso app?"
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Deixe aqui o seu feedback! ;)",
                style = OtherTexts.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (currentStep > 0) {
                    IconButton(
                        modifier = Modifier
                            .size(20.dp),
                        onClick = {
                            if (currentStep > 0) currentStep--
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_left),
                            contentDescription = "Voltar",
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                }

                Text(
                    text = "${questions[currentStep]} (${currentStep + 1}/${questions.size})",
                    style = OtherTexts.headlineLarge,
                    modifier = Modifier.weight(1f)
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            val value = answers[currentStep.toString()] ?: ""
            if (currentStep == questions.lastIndex) {
                OutlinedTextField(
                    value = value,
                    onValueChange = {
                        newValue ->
                        answers = answers.toMutableMap().apply {
                            this[currentStep.toString()] = newValue
                        }
                    },
                    placeholder = { Text(text = "Preencha sua resposta") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blue700,
                        unfocusedBorderColor = Zinc700,
                        cursorColor = Blue700
                    )
                )
            } else {
                OutlinedTextField(
                    value = value,
                    onValueChange = {
                            newValue ->
                        answers = answers.toMutableMap().apply {
                            this[currentStep.toString()] = newValue
                        }
                    },
                    placeholder = { Text(text = "Preencha sua resposta") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blue700,
                        unfocusedBorderColor = Zinc700,
                        cursorColor = Blue700
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            StandardButton(
                onClick = {
                    if (currentStep < questions.lastIndex) {
                        currentStep++
                    } else {
                        println("Respostas: $answers")
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            onDismiss()
                        }
                    }
                },
                text = if (currentStep == questions.lastIndex) "Finalizar" else "Continuar",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun FeedbackModalPreview() {
    FeedbackModal(onDismiss = {})
}
