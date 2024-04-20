package com.example.androidprojecttemplate.feature_login.application

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.androidprojecttemplate.feature_login.domain.auth_request_dto.LoginCredentials
import com.example.androidprojecttemplate.feature_login.domain.events.LoginAlertEvents
import com.example.androidprojecttemplate.feature_login.domain.events.LoginEvents
import com.example.androidprojecttemplate.feature_login.domain.state.LoginTextFieldState
import com.example.androidprojecttemplate.feature_login.domain.use_cases.AuthUseCase
import com.example.androidprojecttemplate.feature_login.domain.validations.validateEmailId
import com.example.androidprojecttemplate.feature_login.domain.validations.validatePassword
import com.example.androidprojecttemplate.BuildConfig
import com.example.androidprojecttemplate.core.networking.exceptions.NetworkExceptions.*
import com.example.androidprojecttemplate.core.networking.request_status.RequestStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _emailIdState = mutableStateOf(
        LoginTextFieldState(
            data = if (BuildConfig.DEBUG) "test@test.com" else "",
            hint = "Email id"
        )
    )
    val emailIdState: State<LoginTextFieldState> = _emailIdState

    private val _passwordState = mutableStateOf(
        LoginTextFieldState(
            data = if (BuildConfig.DEBUG) "test" else "",
            hint = "Password"
        )
    )
    val passwordState: State<LoginTextFieldState> = _passwordState

    private val _requestStatus = mutableStateOf(RequestStatus.Idle)
    val requestStatus: State<RequestStatus> = _requestStatus

    private val _loginAlertEventFlow = MutableSharedFlow<LoginAlertEvents>()
    val loginAlertEventFlow = _loginAlertEventFlow.asSharedFlow()

    fun onEvent(event: LoginEvents, navController: NavController? = null) {
        when (event) {
            is LoginEvents.OnEmailIdInput -> {
                val emailId = event.data
                val isValidEmailId = validateEmailId(emailId)
                _emailIdState.value = _emailIdState.value.copy(
                    data = emailId,
                    dataIsError = isValidEmailId,
                )
            }
            is LoginEvents.OnPasswordInput -> {
                val password = event.data
                val isValidPassword = validatePassword(password)
                _passwordState.value = _passwordState.value.copy(
                    data = password,
                    dataIsError = isValidPassword,
                )
            }
            is LoginEvents.OnSignInClicked -> {
                val username = _emailIdState.value.data
                val password = _passwordState.value.data
                loginUser(username, password)
            }
        }
    }

    private fun loginUser(
        username: String,
        password: String,
    ) {
        viewModelScope.launch {
            val credentials = LoginCredentials(username = username, password = password)
            val result = authUseCase.login(credentials, _requestStatus)
            val resultToUI = result.fold({ error ->
                when (error) {
                    is BadRequest -> Pair(
                        true,
                        "The username or password is incorrect!"
                    )
                    else -> Pair(true, error.exceptionMessage)
                }
            }, {
                Pair(false, "Login Success")
            })

            _loginAlertEventFlow.emit(
                LoginAlertEvents.ShowSnackBar(
                    message = resultToUI.second,
                    isError = resultToUI.first
                )
            )
        }
    }
}

