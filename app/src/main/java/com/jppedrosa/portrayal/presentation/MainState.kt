package com.jppedrosa.portrayal.presentation

import com.jppedrosa.portrayal.data.remote.dto.Image

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 02/09/2022.
 */
data class MainState(
    val isLoading: Boolean = false,
    val images: List<Image>? = emptyList(),
    val error: String? = null
)