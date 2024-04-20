package com.example.androidprojecttemplate.core.networking.api_service

import com.example.androidprojecttemplate.BuildConfig
import com.example.androidprojecttemplate.core.data_store.implementation.SharedPrefsService
import com.example.androidprojecttemplate.core.networking.interceptor.AuthInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiService {

    @Provides
    @Singleton
    fun provideHttpClient(sharedPrefsService: SharedPrefsService): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1
        val client = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .dispatcher(dispatcher)
            .addInterceptor(AuthInterceptor(sharedPrefsService))
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(interceptor)
        }
        return client.build();
    }

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
            .setLenient() // disables accept only json
            .disableHtmlEscaping()
            .create()
        return GsonConverterFactory.create(gsonBuilder)
    }

    @Provides
    @Singleton
    fun provideAndroidTemplateApi(
        httpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
}