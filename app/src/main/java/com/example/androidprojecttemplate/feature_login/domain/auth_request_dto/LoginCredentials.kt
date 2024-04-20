package com.example.androidprojecttemplate.feature_login.domain.auth_request_dto

import com.google.gson.annotations.SerializedName

class LoginCredentials constructor(
    username: String,
    password: String
) {
    @SerializedName("username")
    private val _username: String = username

    @SerializedName("password")
    private val _password: String = password

    val username: String
        get() = _username

    val password: String
        get() = _password
}
