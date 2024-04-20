package com.example.androidprojecttemplate.core.routes

sealed class AndroidTemplateRoutes(val routeName: String) {

    // Splash Route
    data object Splash : AndroidTemplateRoutes("splash")

    // Login Route
    data object Login : AndroidTemplateRoutes("login")

    // Home Route
    data object Home : AndroidTemplateRoutes("home")
}