package com.jppedrosa.portrayal.domain.repository

import com.jppedrosa.portrayal.data.remote.dto.Image

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 02/09/2022.
 */

interface PortrayalRepository {

    suspend fun getImages(orderBy: String, page: Int, limit: Int): List<Image>

}