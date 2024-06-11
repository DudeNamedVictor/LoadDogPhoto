package com.example.loaddogphoto.ui.navigationgraphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.loaddogphoto.ui.breedimagescreen.BreedScreen
import com.example.loaddogphoto.ui.breedslistscreen.BreedsListScreen
import com.example.loaddogphoto.ui.navigationgraphs.BreedsNavObject.DOG_BREED
import com.example.loaddogphoto.ui.navigationgraphs.BreedsNavObject.Routes.BREED_WITH_ARGS
import com.example.loaddogphoto.ui.navigationgraphs.BreedsNavObject.Routes.BREEDS_LIST


@Composable
fun BreedsNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = BREEDS_LIST
    ) {
        composable(route = BREEDS_LIST) {
            BreedsListScreen { navController.navigate(it) }
        }
        composable(
            route = BREED_WITH_ARGS,
            arguments = listOf(navArgument(name = DOG_BREED) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val breed = requireNotNull(backStackEntry.arguments?.getString(DOG_BREED))
            BreedScreen(breed)
        }
    }
}

object BreedsNavObject {
    const val DOG_BREED = "dog_breed"

    object Routes {
        const val BREED = "breed"
        const val BREEDS_LIST = "breeds_list"
        const val BREED_WITH_ARGS = "$BREED/{$DOG_BREED}"
    }
}