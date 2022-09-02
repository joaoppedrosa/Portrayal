package com.jppedrosa.portrayal.domain.uc.gimages

import com.jppedrosa.portrayal.common.Resource
import com.jppedrosa.portrayal.data.remote.dto.Image
import com.jppedrosa.portrayal.domain.repository.PortrayalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 02/09/2022.
 */
class GetImagesUseCase @Inject constructor(
    private val repository: PortrayalRepository
) {
    operator fun invoke(orderBy: String, page: Int, limit: Int): Flow<Resource<List<Image>>> =
        flow {
            try {
                emit(Resource.Loading())
                val images = repository.getImages(orderBy, page, limit)
                emit(Resource.Success(images))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
            } catch (e: IOException) {
                emit(Resource.Error("Server unreachable. Verify your internet connection"))
            }
        }
}