package com.example.cardiosurgeryillustrator.ui.screens.patient

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.models.chatbot.ChatMessage
import com.example.cardiosurgeryillustrator.ui.screens.patient.chatbot.ChatBotApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssistantScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val CustomBlue = Color(0xFF0074B7)
    var messageText by remember { mutableStateOf(TextFieldValue()) }
    val messages = remember { mutableStateListOf<ChatMessage>() }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    if (messages.isEmpty()) {
        messages.add(
            ChatMessage(
                text = "Olá! Sou o assistente virtual. Fui desenvolvido para tirar dúvidas pontuais. Fique à vontade para perguntar!",
                isUser = false
            )
        )
    }

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
                    MessageBubble(
                        content = message.text,
                        isUserMessage = message.isUser,
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
                            val userMessage = ChatMessage(messageText.text, isUser = true)
                            messages.add(userMessage)
                            coroutineScope.launch {
                                val response = ChatBotApi.sendMessage(userMessage.text)
                                response?.let {
                                    messages.add(ChatMessage(it, isUser = false))
                                }
                            }
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

@Composable
fun MessageBubble(content: String, isUserMessage: Boolean, customBlue: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUserMessage) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (isUserMessage) {
                        customBlue
                    } else {
                        MaterialTheme.colorScheme.secondary
                    },
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(12.dp)
                .widthIn(max = 250.dp)
        ) {
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isUserMessage) {
                    Color.White
                } else {
                    MaterialTheme.colorScheme.onSecondary
                }
            )
        }
    }
}

@Preview
@Composable
private fun AssistantScreenPreview() {
    AssistantScreen(
        modifier = Modifier.fillMaxWidth(),
        navController = rememberNavController()
    )
}
