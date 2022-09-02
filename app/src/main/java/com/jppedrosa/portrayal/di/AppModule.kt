package com.jppedrosa.portrayal.di

import com.jppedrosa.portrayal.common.Constants
import com.jppedrosa.portrayal.data.remote.PortrayalApi
import com.jppedrosa.portrayal.data.repository.PortrayalRepositoryImpl
import com.jppedrosa.portrayal.domain.repository.PortrayalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 02/09/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePortrayalApi(): PortrayalApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PortrayalApi::class.java)
    }

    @Provides
    @Singleton
    fun providePortrayalRepository(api: PortrayalApi): PortrayalRepository {
        return PortrayalRepositoryImpl(api);
    }
}