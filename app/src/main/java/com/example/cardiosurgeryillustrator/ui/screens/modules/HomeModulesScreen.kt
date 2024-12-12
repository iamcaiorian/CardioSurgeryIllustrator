package com.example.cardiosurgeryillustrator.ui.screens.modules

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cardiosurgeryillustrator.ui.components.topBar.TopBarModules

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeModulesScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopBarModules(
                navController = navController,
                onHelpClick = { /* Handle Help Click */ },
                onSettingsClick = { /* Handle Settings Click */ }
            )
        }
    ) { innerPadding ->
        Row(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "MODULES",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

