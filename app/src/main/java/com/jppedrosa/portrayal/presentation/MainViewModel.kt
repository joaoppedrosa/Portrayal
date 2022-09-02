package com.jppedrosa.portrayal.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jppedrosa.portrayal.common.Resource
import com.jppedrosa.portrayal.domain.repository.PortrayalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 02/09/2022.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PortrayalRepository
) : ViewModel() {

    var state by mutableStateOf(MainState())
        private set

    fun getImages() {
        val orderBy = "relevant"
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            when (val result = repository.getImages(orderBy, 1, 50)) {
                is Resource.Success<*> -> {
                    state = state.copy(
                        images = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        images = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}