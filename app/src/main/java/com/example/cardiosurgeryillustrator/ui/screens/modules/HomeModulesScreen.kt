package com.example.cardiosurgeryillustrator.ui.screens.modules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.models.mock.mockSubjects
import com.example.cardiosurgeryillustrator.ui.components.modules.SubjectCardList
import com.example.cardiosurgeryillustrator.ui.components.modules.TopBarModules

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
        SubjectCardList(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            subjectList = mockSubjects,
            onSubjectClick = {}
        )
    }
}

@Preview
@Composable
private fun HomeModulesScreenPreview() {
    HomeModulesScreen(modifier = Modifier, navController = rememberNavController())
}