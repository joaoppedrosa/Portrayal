package com.jppedrosa.portrayal.data.repository

import com.jppedrosa.portrayal.common.Resource
import javax.inject.Inject
import com.jppedrosa.portrayal.data.remote.PortrayalApi
import com.jppedrosa.portrayal.data.remote.dto.Image
import com.jppedrosa.portrayal.domain.repository.PortrayalRepository

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 02/09/2022.
 */
class PortrayalRepositoryImpl @Inject constructor(
    private val api: PortrayalApi
) : PortrayalRepository {

    override suspend fun getImages(orderBy: String, page: Int, limit: Int): Resource<List<Image>> {
        return try {
            Resource.Success(
                data = api.getImages(
                    orderBy = orderBy,
                    page = page,
                    limit = limit
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Unknown error")
        }
    }
}