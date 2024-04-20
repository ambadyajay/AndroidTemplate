package com.example.androidprojecttemplate.feature_login.infrastructure

import androidx.compose.runtime.MutableState
import arrow.core.Either
import com.example.androidprojecttemplate.core.data_store.implementation.SharedPrefsService
import com.example.androidprojecttemplate.core.data_store.keys.SHARED_PREF_KEY_AUTH_TOKEN
import com.example.androidprojecttemplate.core.data_store.keys.SHARED_PREF_KEY_PASSWORD
import com.example.androidprojecttemplate.core.data_store.keys.SHARED_PREF_KEY_USERNAME
import com.example.androidprojecttemplate.core.networking.exceptions.NetworkExceptions
import com.example.androidprojecttemplate.core.networking.execute.NetworkRequest
import com.example.androidprojecttemplate.core.networking.request_status.RequestStatus
import com.example.androidprojecttemplate.feature_login.domain.auth_request_dto.LoginCredentials
import com.example.androidprojecttemplate.feature_login.domain.service.AuthenticationService
import kotlinx.coroutines.flow.first


class AuthenticationRepository(
    private val authService: AuthenticationService,
    private val sharedPrefsService: SharedPrefsService
) {
    suspend fun login(
        credentials: LoginCredentials,
        status: MutableState<RequestStatus>
    ): Either<NetworkExceptions, Unit> {
        sharedPrefsService.putPreference(SHARED_PREF_KEY_USERNAME, credentials.username)
        sharedPrefsService.putPreference(SHARED_PREF_KEY_PASSWORD, credentials.password)
        val result = NetworkRequest.safeExecute(
            status = status,
            method = {
                authService.login(credentials)
            })
        return result.fold<Either<NetworkExceptions, Unit>>(
            ifLeft = {
                Either.Left(it)
            },
            ifRight = {
                val token = it.result?.accessToken
                if (token != null) {
                    sharedPrefsService.putPreference(SHARED_PREF_KEY_AUTH_TOKEN, token)
                    Either.Right(Unit)
                } else {
                    Either.Left(NetworkExceptions.UnknownError)
                }

            }
        )
    }

    suspend fun isUserLoggedIn(): Boolean {
        val result = sharedPrefsService.getPreference(
            key = SHARED_PREF_KEY_AUTH_TOKEN,
            defaultValue = "",
        ).first()
        return result != null
    }

}