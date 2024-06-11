package com.example.loaddogphoto.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loaddogphoto.utils.saveImageIntoDownloads
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus


abstract class BaseViewModel : ViewModel() {
    protected val _viewState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val viewState: StateFlow<ScreenState> = _viewState.asStateFlow()

    protected val baseViewModelScope =
        viewModelScope + Dispatchers.IO + CoroutineExceptionHandler { _, _ ->
            _viewState.value = ScreenState.Error
        }

    fun saveImage(imageName: String) {
        baseViewModelScope.launch {
            saveImageIntoDownloads(imageName)
        }
    }

    sealed class ScreenState {
        data class Success<T>(val viewState: T) : ScreenState()
        data object Loading : ScreenState()
        data object Error : ScreenState()
    }
}