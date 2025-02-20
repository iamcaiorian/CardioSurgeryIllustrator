package com.example.cardiosurgeryillustrator.ui.screens.patient.assistant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.repository.patient.assistent.AssistantRepository
import com.example.cardiosurgeryillustrator.ui.components.patient.assistent.AssistantMessageBubble
import com.example.cardiosurgeryillustrator.ui.components.patient.assistent.AssistantTypingBubble
import com.example.cardiosurgeryillustrator.ui.components.patient.message_bottom.MessageBottomBar
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.view_models.patient.assistent.AssistantViewModel
import com.example.cardiosurgeryillustrator.view_models.patient.assistent.AssistantViewModelFactory

@Composable
fun AssistantScreen(
    modifier: Modifier = Modifier,
    viewModel: AssistantViewModel = viewModel(
        factory = AssistantViewModelFactory(AssistantRepository())
    )
) {
    val CustomBlue = Color(0xFF0074B7)
    val messages by viewModel.messages.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var messageText by remember { mutableStateOf(TextFieldValue()) }
    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        listState.animateScrollToItem(messages.size)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(8.dp),
            state = listState
        ) {
            items(messages) { message ->
                AssistantMessageBubble(
                    content = message.message,
                    isUserMessage = message.sender == "user",
                )
            }

            if (isLoading) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        AssistantTypingBubble()
                    }
                }
            }
        }

        MessageBottomBar(
            messageText = messageText,
            onMessageTextChange = { messageText = it },
            onSendClick = {
                if (messageText.text.isNotEmpty() && !isLoading) {
                    viewModel.sendMessage(messageText.text)
                    messageText = TextFieldValue("")
                }
            },
            placeholder = if (isLoading) "Aguarde..." else "Digite aqui sua pergunta..."
        )
    }
}

@Preview
@Composable
private fun AssistantScreenPreview() {
    AssistantScreen(
        modifier = Modifier.fillMaxWidth(),
    )
}
