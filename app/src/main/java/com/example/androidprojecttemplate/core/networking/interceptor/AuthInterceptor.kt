package com.example.androidprojecttemplate.core.networking.interceptor

import com.example.androidprojecttemplate.BuildConfig
import com.example.androidprojecttemplate.core.data_store.implementation.SharedPrefsService
import com.example.androidprojecttemplate.core.data_store.keys.SHARED_PREF_KEY_AUTH_TOKEN
import com.example.androidprojecttemplate.core.data_store.keys.SHARED_PREF_KEY_PASSWORD
import com.example.androidprojecttemplate.core.data_store.keys.SHARED_PREF_KEY_USERNAME
import com.example.androidprojecttemplate.core.networking.execute.NetworkRequest
import com.example.androidprojecttemplate.feature_login.domain.auth_request_dto.LoginCredentials
import com.example.androidprojecttemplate.feature_login.domain.service.AuthenticationService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AuthInterceptor(private val sharedPrefsService: SharedPrefsService) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithAuthToken = runBlocking {
            appendAuthToken(originalRequest)
        }

        val initialResponse = chain.proceed(requestWithAuthToken)
        when (initialResponse.code) {
            401 -> {

                runBlocking {
                    val retrofit = Retrofit.Builder()
                        .baseUrl(BuildConfig.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                    val username =
                        sharedPrefsService.getPreference(SHARED_PREF_KEY_USERNAME, "").first()

                    val password =
                        sharedPrefsService.getPreference(SHARED_PREF_KEY_PASSWORD, "").first()
                    val result = NetworkRequest.safeExecute(
                        method = {
                            retrofit.create(AuthenticationService::class.java)
                                .login(LoginCredentials(username ?: "", password ?: ""))
                        }
                    )
                    result.fold(
                        ifLeft = {},
                        ifRight = {
                            val token = it.result?.accessToken
                            if (token != null) {
                                sharedPrefsService.putPreference(SHARED_PREF_KEY_AUTH_TOKEN, token)
                            }
                        })
                }
                val requestWithAuthToken = runBlocking {
                    appendAuthToken(originalRequest)
                }

                return chain.proceed(requestWithAuthToken)


            }
            else -> return initialResponse
        }
    }

    private suspend fun appendAuthToken(req: Request): Request {
        val token = sharedPrefsService.getPreference(SHARED_PREF_KEY_AUTH_TOKEN, "").first()
        return req.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
    }
}