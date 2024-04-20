package com.example.androidprojecttemplate.feature_login.domain.validations

import android.util.Patterns

fun validateEmailId(value: String): Boolean {
    return !Patterns.EMAIL_ADDRESS.matcher(value).matches()
}

fun validatePassword(value: String): Boolean {
    return value.trim().length < 8
}