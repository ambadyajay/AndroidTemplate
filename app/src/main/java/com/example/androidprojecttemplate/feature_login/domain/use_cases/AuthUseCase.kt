package com.example.androidprojecttemplate.feature_login.domain.use_cases


data class AuthUseCase(
    val login: LoginUseCase,
    val checkLogin: CheckLoginUseCase,
)
