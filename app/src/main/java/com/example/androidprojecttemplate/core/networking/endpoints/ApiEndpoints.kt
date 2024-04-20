package com.example.androidprojecttemplate.core.networking.endpoints

import com.example.androidprojecttemplate.BuildConfig

abstract class ApiEndpoints {
    companion object {
        const val version = BuildConfig.API_VERSION
        const val urlLogin = "/todos/1"
    }
}