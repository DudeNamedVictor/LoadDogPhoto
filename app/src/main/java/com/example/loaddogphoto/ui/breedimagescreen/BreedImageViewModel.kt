package com.example.loaddogphoto.ui.breedimagescreen

import com.example.loaddogphoto.data.Network
import com.example.loaddogphoto.ui.BaseViewModel
import kotlinx.coroutines.launch


class BreedImageViewModel(private val breed: String): BaseViewModel() {

    init {
        getBreedImage()
    }

    fun getBreedImage() {
        _viewState.value = ScreenState.Loading

        baseViewModelScope.launch {
            val image = Network.networkConnection.getBreedImage(breed)
            _viewState.value = ScreenState.Success(
                BreedImageScreenState(
                    breed.replaceFirstChar { it.uppercase() },
                    image.message
                )
            )
        }
    }

    data class BreedImageScreenState(
        val breed: String,
        val image: String
    )
}