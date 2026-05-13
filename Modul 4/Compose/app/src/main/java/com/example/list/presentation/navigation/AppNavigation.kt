package com.example.list.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.list.data.ComicDataSource
import com.example.list.presentation.screens.DetailScreen
import com.example.list.presentation.screens.HomeScreen
import com.example.list.presentation.screens.LanguageScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable(
            route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            val comic = ComicDataSource.dummyComics.find { it.id == id }
            comic?.let { DetailScreen(it, navController) }
        }
        composable("language") { LanguageScreen(navController) }
    }
}