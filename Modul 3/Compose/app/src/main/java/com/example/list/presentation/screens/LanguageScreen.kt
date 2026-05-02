package com.example.list.presentation.screens

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavController
import com.example.list.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.change_language)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("id")
                    AppCompatDelegate.setApplicationLocales(appLocale)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.indonesian))
            }

            Button(
                onClick = {
                    val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("en")
                    AppCompatDelegate.setApplicationLocales(appLocale)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.english))
            }
        }
    }
}