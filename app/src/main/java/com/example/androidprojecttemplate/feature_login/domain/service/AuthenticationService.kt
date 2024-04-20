package com.example.androidprojecttemplate.feature_login.domain.service

import com.example.androidprojecttemplate.core.networking.endpoints.ApiEndpoints
import com.example.androidprojecttemplate.core.networking.response.AndroidTemplateResponse
import com.example.androidprojecttemplate.feature_login.domain.auth_request_dto.LoginCredentials
import com.example.androidprojecttemplate.feature_login.domain.auth_response_dto.AuthResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    @POST(ApiEndpoints.urlLogin)
    suspend fun login(@Body credentials: LoginCredentials): AndroidTemplateResponse<AuthResponseDto>
}