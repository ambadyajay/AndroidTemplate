package com.example.androidprojecttemplate.feature_splash.application

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidprojecttemplate.feature_login.domain.use_cases.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _loginSharedFlow = MutableSharedFlow<Boolean>()
    val loginSharedFlow = _loginSharedFlow.asSharedFlow()


    fun checkUserLoggedIn() {
        viewModelScope.launch {
            val result = authUseCase.checkLogin()
            _loginSharedFlow.emit(result)
        }

    }
}