package com.example.loaddogphoto.ui.breedslistscreen

import com.example.loaddogphoto.data.Network
import com.example.loaddogphoto.ui.BaseViewModel
import kotlinx.coroutines.launch


class BreedsListViewModel : BaseViewModel() {

    init {
        baseViewModelScope.launch {
            val breeds = Network.networkConnection.getBreedsList()
            _viewState.value =
                ScreenState.Success(BreedsListScreenState(breeds.message.keys.toList()))
        }
    }

    data class BreedsListScreenState(
        val breeds: List<String>
    )
}