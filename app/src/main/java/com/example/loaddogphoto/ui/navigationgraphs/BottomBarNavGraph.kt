package com.example.loaddogphoto.ui.navigationgraphs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loaddogphoto.ui.dogimagescreen.DogImageScreen


@Composable
fun BottomBarNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = BottomBarScreen.Dog.route
    ) {
        composable(route = BottomBarScreen.Dog.route) {
            DogImageScreen()
        }
        composable(route = BottomBarScreen.Breeds.route) {
            val breedsNavController = rememberNavController()
            BreedsNavGraph(
                modifier = Modifier.fillMaxSize(),
                navController = breedsNavController
            )
        }
    }
}

sealed class BottomBarScreen(var route: String, var icon: ImageVector, var title: String) {
    data object Dog : BottomBarScreen(route = "dog", icon = Icons.Default.Home, title = "Dog")
    data object Breeds :
        BottomBarScreen(route = "breeds", icon = Icons.Default.Settings, title = "Breeds")
}