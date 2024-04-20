package com.example.androidprojecttemplate.feature_login.domain.module

import com.example.androidprojecttemplate.feature_login.domain.service.AuthenticationService
import com.example.androidprojecttemplate.feature_login.domain.use_cases.CheckLoginUseCase
import com.example.androidprojecttemplate.feature_login.infrastructure.AuthenticationRepository
import com.example.androidprojecttemplate.core.data_store.implementation.SharedPrefsService
import com.example.androidprojecttemplate.feature_login.domain.use_cases.AuthUseCase
import com.example.androidprojecttemplate.feature_login.domain.use_cases.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthenticationService {
        return retrofit.create(AuthenticationService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authenticationService: AuthenticationService,
        sharedPrefsService: SharedPrefsService
    ): AuthenticationRepository {
        return AuthenticationRepository(authenticationService, sharedPrefsService)
    }

    @Provides
    @Singleton
    fun provideAuthUseCase(
        authenticationRepository: AuthenticationRepository,
    ): AuthUseCase {
        return AuthUseCase(
            login = LoginUseCase(authenticationRepository),
            checkLogin = CheckLoginUseCase(authenticationRepository),
        )
    }

}