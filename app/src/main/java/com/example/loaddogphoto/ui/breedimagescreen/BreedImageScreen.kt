package com.example.loaddogphoto.ui.breedimagescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.loaddogphoto.R
import com.example.loaddogphoto.ui.BaseViewModel
import com.example.loaddogphoto.ui.composecomponents.CustomAppBar
import com.example.loaddogphoto.ui.composecomponents.ErrorComponent
import com.example.loaddogphoto.ui.composecomponents.ProgressIndicator


@Composable
fun BreedScreen(breed: String) {
    val viewModel: BreedImageViewModel = viewModel { BreedImageViewModel(breed) }
    val state by viewModel.viewState.collectAsState()

    BreedScreenState(viewModel) { state }
}

@Composable
fun BreedScreenState(
    viewModel: BreedImageViewModel,
    state: () -> BaseViewModel.ScreenState
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val stateValue = state()) {
            BaseViewModel.ScreenState.Loading -> ProgressIndicator()

            is BaseViewModel.ScreenState.Success<*> -> {
                val breedState = stateValue.viewState as BreedImageViewModel.BreedImageScreenState
                Column {
                    CustomAppBar(breedState.breed)
                    Box {
                        Image(
                            painter = rememberAsyncImagePainter(breedState.image),
                            modifier = Modifier.fillMaxSize(),
                            contentDescription = null
                        )
                        Row(modifier = Modifier.align(Alignment.BottomCenter)) {
                            Button(onClick = { viewModel.getBreedImage() }
                            ) {
                                Text(stringResource(id = R.string.update_image))
                            }
                            Spacer(modifier = Modifier.size(12.dp))
                            Button(onClick = { viewModel.saveImage(breedState.image) }
                            ) {
                                Text(stringResource(id = R.string.download_photo))
                            }
                        }
                    }
                }
            }

            is BaseViewModel.ScreenState.Error -> ErrorComponent()
        }
    }
}