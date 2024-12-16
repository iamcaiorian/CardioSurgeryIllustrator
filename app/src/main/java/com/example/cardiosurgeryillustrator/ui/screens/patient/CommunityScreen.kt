package com.example.cardiosurgeryillustrator.ui.screens.patient

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun CommunityScreen(modifier: Modifier = Modifier, navController: NavController) {
    Text(text = "Comunnidade")
}

@Preview
@Composable
private fun CommunityScreenPreview() {
    CommunityScreen(modifier = Modifier.fillMaxWidth(), navController = rememberNavController())
}