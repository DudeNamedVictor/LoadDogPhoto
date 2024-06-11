package com.example.loaddogphoto.ui.breedslistscreen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loaddogphoto.R
import com.example.loaddogphoto.ui.BaseViewModel
import com.example.loaddogphoto.ui.breedslistscreen.BreedsScreenObject.colors
import com.example.loaddogphoto.ui.composecomponents.CustomAppBar
import com.example.loaddogphoto.ui.composecomponents.ErrorComponent
import com.example.loaddogphoto.ui.composecomponents.ProgressIndicator
import com.example.loaddogphoto.ui.navigationgraphs.BreedsNavObject.Routes.BREED


@Composable
fun BreedsListScreen(route: (String) -> Unit) {
    val viewModel = viewModel<BreedsListViewModel>()
    val state by viewModel.viewState.collectAsState()

    BreedsListScreenState(route) { state }
}

@Composable
fun BreedsListScreenState(
    route: (String) -> Unit,
    state: () -> BaseViewModel.ScreenState
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val stateValue = state()) {
            BaseViewModel.ScreenState.Loading -> ProgressIndicator()

            is BaseViewModel.ScreenState.Success<*> -> {
                val breeds =
                    (stateValue.viewState as BreedsListViewModel.BreedsListScreenState).breeds
                Column {
                    CustomAppBar(stringResource(id = R.string.choose_breed))
                    BreedsList(
                        route = route,
                        breeds = breeds
                    )
                }
            }

            is BaseViewModel.ScreenState.Error -> ErrorComponent()
        }
    }
}

@Composable
fun BreedsList(
    route: (String) -> Unit,
    breeds: List<String>
) {
    LazyColumn {
        items(breeds) { breed ->
            Row(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            brush = Brush.linearGradient(colors = colors),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable { route("$BREED/${breed}") },
                    text = breed,
                    fontSize = 32.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

object BreedsScreenObject {
    val colors = listOf(
        Color.Red,
        Color.Yellow,
        Color.Green,
        Color.Cyan
    )
}