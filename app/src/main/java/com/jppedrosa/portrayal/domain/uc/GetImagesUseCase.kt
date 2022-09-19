package com.jppedrosa.portrayal.domain.uc

import com.jppedrosa.portrayal.common.Resource
import com.jppedrosa.portrayal.domain.repository.PortrayalRepository
import javax.inject.Inject
import com.jppedrosa.portrayal.data.remote.dto.Image
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 19/09/2022.
 */
class GetImagesUseCase @Inject constructor(
    private val repository: PortrayalRepository
) {
    operator fun invoke(): Flow<Resource<List<Image>>> = flow {
        try {
            emit(Resource.Loading())
            val images = repository.getImages("relevant", 1, 50)
            emit(Resource.Success(images))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error!"))
        } catch (e: IOException) {
            emit(Resource.Error("Check your internet connection"))
        }
    }
}