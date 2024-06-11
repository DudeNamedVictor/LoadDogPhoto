package com.example.loaddogphoto.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.loaddogphoto.ui.MainActivity.Companion.screens
import com.example.loaddogphoto.ui.navigationgraphs.BottomBarNavGraph
import com.example.loaddogphoto.ui.navigationgraphs.BottomBarScreen
import com.example.loaddogphoto.ui.theme.LoadDogPhotoTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            LoadDogPhotoTheme {
                Scaffold(
                    bottomBar = { BottomBar(navController = navController) }
                ) {
                    BottomBarNavGraph(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        navController = navController
                    )
                }
            }
        }
    }

    companion object {
        val screens: List<BottomBarScreen> = listOf(BottomBarScreen.Dog, BottomBarScreen.Breeds)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                navDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    navDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "NavBar Icon")
        },
        label = {
            Text(text = screen.title)
        },
        selected = navDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navController.navigate(screen.route)
        })
}