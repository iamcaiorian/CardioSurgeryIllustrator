package com.example.cardiosurgeryillustrator.ui.screens.modules

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HomeModulesScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Log.d("HomeModulesScreen", "Rendered")
    Row(modifier = modifier) {
        Text("MODULES")
    }
}
