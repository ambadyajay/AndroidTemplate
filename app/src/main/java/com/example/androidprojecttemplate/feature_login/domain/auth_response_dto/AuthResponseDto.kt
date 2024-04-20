package com.example.androidprojecttemplate.feature_login.domain.auth_response_dto

import com.google.gson.annotations.SerializedName

data class AuthResponseDto(
    @SerializedName("access_token")
    val accessToken: String?
)
