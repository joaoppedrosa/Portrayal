package com.jppedrosa.portrayal.di

import com.jppedrosa.portrayal.BuildConfig
import com.jppedrosa.portrayal.common.Constants
import com.jppedrosa.portrayal.data.remote.PortrayalApi
import com.jppedrosa.portrayal.data.repository.PortrayalRepositoryImpl
import com.jppedrosa.portrayal.domain.repository.PortrayalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
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
        val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val original: Request = chain.request()
            val request: Request = original.newBuilder()
                .header("Content-Type", "application/json")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        })
        httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val request: Request = chain.request()
            val newRequest: Request = request.newBuilder()
                .addHeader("Authorization", "Client-ID " + BuildConfig.UNSPLASH_API_KEY)
                .build()
            chain.proceed(newRequest)
        })
        val okHttpClient = httpClient.build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
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