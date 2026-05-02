package com.example.list.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.list.R
import com.example.list.data.MovieDataSource
import com.example.list.presentation.components.CarouselItem
import com.example.list.presentation.components.MovieListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val movies = MovieDataSource.dummyMovies

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate("language") }) {
                        Icon(
                            Icons.Default.Language,
                            contentDescription = stringResource(R.string.language)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    actionIconContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                Text(
                    text = stringResource(R.string.highlight),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    items(movies) { movie -> CarouselItem(movie, navController) }
                }
            }
            items(movies) { movie ->
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    MovieListItem(movie, navController)
                }
            }
        }
    }
}