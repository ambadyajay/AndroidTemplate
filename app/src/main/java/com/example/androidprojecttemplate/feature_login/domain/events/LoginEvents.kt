package com.example.androidprojecttemplate.feature_login.domain.events

sealed class LoginEvents {
    data class OnEmailIdInput(val data: String) : LoginEvents()
    data class OnPasswordInput(val data: String) : LoginEvents()
    data object OnSignInClicked : LoginEvents()
}

sealed class LoginAlertEvents {
    data class ShowSnackBar(val message: String, val isError: Boolean) : LoginAlertEvents()
}