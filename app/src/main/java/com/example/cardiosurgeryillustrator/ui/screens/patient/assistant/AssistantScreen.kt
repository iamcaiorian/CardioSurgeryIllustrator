package com.example.cardiosurgeryillustrator.ui.screens.patient.assistant

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.repository.patient.assistent.AssistantRepository
import com.example.cardiosurgeryillustrator.ui.components.patient.assistent.AssistantMessageBubble
import com.example.cardiosurgeryillustrator.view_models.patient.assistent.AssistantViewModel
import com.example.cardiosurgeryillustrator.view_models.patient.assistent.AssistantViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssistantScreen(
    modifier: Modifier = Modifier,
    viewModel: AssistantViewModel = viewModel(
        factory = AssistantViewModelFactory(AssistantRepository())
    )
) {
    val CustomBlue = Color(0xFF0074B7)
    val messages by viewModel.messages.collectAsState()
    var messageText by remember { mutableStateOf(TextFieldValue()) }
    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        listState.animateScrollToItem(messages.size)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Assistente",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = CustomBlue
                )
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                state = listState
            ) {
                items(messages) { message ->
                    Spacer(modifier = Modifier.height(8.dp))
                    AssistantMessageBubble(
                        content = message.message,
                        isUserMessage = message.sender == "user",
                        customBlue = CustomBlue
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            MaterialTheme.colorScheme.surface,
                            RoundedCornerShape(20.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = CustomBlue,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        if (messageText.text.isNotEmpty()) {
                            viewModel.sendMessage(messageText.text)
                            messageText = TextFieldValue("")
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Enviar",
                        tint = CustomBlue
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun AssistantScreenPreview() {
    AssistantScreen(
        modifier = Modifier.fillMaxWidth(),
    )
}
