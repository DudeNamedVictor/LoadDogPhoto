package com.example.loaddogphoto.ui.dogimagescreen

import com.example.loaddogphoto.data.Network
import com.example.loaddogphoto.ui.BaseViewModel
import kotlinx.coroutines.launch


class DogImageViewModel: BaseViewModel() {

    init {
        getDogImage()
    }

    fun getDogImage() {
        _viewState.value = ScreenState.Loading

        baseViewModelScope.launch {
            val image = Network.networkConnection.getDogImage()
            _viewState.value = ScreenState.Success(DogImageScreenState(image.message))
        }
    }

    data class DogImageScreenState(
        val image: String
    )
}