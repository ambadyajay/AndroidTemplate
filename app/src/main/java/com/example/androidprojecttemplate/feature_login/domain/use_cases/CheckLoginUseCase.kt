package com.example.androidprojecttemplate.feature_login.domain.use_cases

import com.example.androidprojecttemplate.feature_login.infrastructure.AuthenticationRepository


class CheckLoginUseCase(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(
    ): Boolean {
        return authenticationRepository.isUserLoggedIn()
    }
}