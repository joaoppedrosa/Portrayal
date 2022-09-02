package com.jppedrosa.portrayal.data.remote.dto

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 02/09/2022.
 */

data class Image(
    var id: String,
    var color: String,
    var likes: Int,
    var description: String,
    var urls: Urls,
    var user: User
)