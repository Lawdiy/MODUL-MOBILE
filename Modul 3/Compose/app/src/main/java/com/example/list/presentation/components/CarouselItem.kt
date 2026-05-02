package com.example.list.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.list.domain.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselItem(movie: Movie, navController: NavController) {
    Card(
        onClick = { navController.navigate("detail/${movie.id}") },
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.size(180.dp, 100.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Image(
            painter = painterResource(id = movie.imageResId),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}