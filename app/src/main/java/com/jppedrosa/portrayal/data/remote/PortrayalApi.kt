package com.jppedrosa.portrayal.data.remote

import com.jppedrosa.portrayal.data.remote.dto.Image
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 02/09/2022.
 */
interface PortrayalApi {

    @GET("/photos")
    suspend fun getImages(
        @Query("order_by") orderBy: String,
        @Query("page") page: Int,
        @Query("per_page") limit: Int
    ): List<Image>
}