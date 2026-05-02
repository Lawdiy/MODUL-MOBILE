package com.example.list.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.list.data.MovieDataSource
import com.example.list.presentation.screens.DetailScreen
import com.example.list.presentation.screens.HomeScreen
import com.example.list.presentation.screens.LanguageScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            val movie = MovieDataSource.dummyMovies.find { it.id == id }
            movie?.let { DetailScreen(it, navController) }
        }
        composable("language") { LanguageScreen(navController) }
    }
}