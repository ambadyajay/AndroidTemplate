package com.example.androidprojecttemplate.core.networking.execute

import androidx.compose.runtime.MutableState
import arrow.core.Either
import com.example.androidprojecttemplate.core.networking.exceptions.NetworkExceptions
import com.example.androidprojecttemplate.core.networking.request_status.RequestStatus
import com.example.androidprojecttemplate.core.networking.response.AndroidTemplateResponse
import com.example.androidprojecttemplate.core.networking.type_def.RemoteResponse
import retrofit2.HttpException
import java.io.IOException

class NetworkRequest {
    companion object {
        suspend fun <T> safeExecute(
            method: suspend () -> AndroidTemplateResponse<T>,
            status: MutableState<RequestStatus>? = null,
        ): RemoteResponse<T> {

            fun updateStatus(value: RequestStatus) {
                status?.let {
                    status.value = value
                }
            }

            return try {
                updateStatus(RequestStatus.Loading)
                val result = method()
                updateStatus(RequestStatus.Success)
                Either.Right(result)
            } catch (e: HttpException) {
                val statusCode = e.code()
                val exception = when (NetworkExceptions.contains(statusCode)) {
                    true -> NetworkExceptions.get(statusCode)
                    false -> NetworkExceptions.UnknownError
                }
                updateStatus(RequestStatus.Failed)
                Either.Left(exception)

            } catch (e: IOException) {
                updateStatus(RequestStatus.Failed)
                Either.Left(NetworkExceptions.NoInternet)
            }
        }
    }
}