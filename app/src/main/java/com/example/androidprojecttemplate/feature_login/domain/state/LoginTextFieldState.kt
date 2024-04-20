package com.example.androidprojecttemplate.feature_login.domain.state

data class LoginTextFieldState(
    val data: String,
    val hint: String,
    val dataIsError: Boolean = false,
)
