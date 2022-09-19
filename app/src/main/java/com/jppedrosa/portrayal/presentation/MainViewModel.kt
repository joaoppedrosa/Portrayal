package com.jppedrosa.portrayal.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jppedrosa.portrayal.common.Resource
import com.jppedrosa.portrayal.domain.uc.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 02/09/2022.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    init {
        getImages()
    }

    private fun getImages() {
        this.getImagesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    this._state.value = MainState(images = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    this._state.value = MainState(error = result.message ?: "Unexpected error!")
                }
                is Resource.Loading -> {
                    this._state.value = MainState(isLoading = true)
                }
            }
        }.launchIn(this.viewModelScope)
    }
}