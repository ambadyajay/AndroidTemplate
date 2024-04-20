package com.example.androidprojecttemplate.feature_login.domain.use_cases

import androidx.compose.runtime.MutableState
import arrow.core.Either
import com.example.androidprojecttemplate.feature_login.domain.auth_request_dto.LoginCredentials
import com.example.androidprojecttemplate.feature_login.infrastructure.AuthenticationRepository
import com.example.androidprojecttemplate.core.networking.exceptions.NetworkExceptions
import com.example.androidprojecttemplate.core.networking.request_status.RequestStatus

class LoginUseCase(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(
        credentials: LoginCredentials,
        status: MutableState<RequestStatus>
    ): Either<NetworkExceptions, Unit> {
        return authenticationRepository.login(
            credentials = credentials,
            status = status
        )
    }
}