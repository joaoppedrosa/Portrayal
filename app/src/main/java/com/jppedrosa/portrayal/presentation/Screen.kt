package com.jppedrosa.portrayal.presentation

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 19/09/2022.
 */
sealed class Screen(val route: String) {
    object CoinListScreen: Screen("image_list_screen")
}