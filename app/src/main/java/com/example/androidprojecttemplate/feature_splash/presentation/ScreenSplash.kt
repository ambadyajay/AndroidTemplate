package com.example.androidprojecttemplate.feature_splash.presentation

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidprojecttemplate.components.AndroidTemplateLogo
import com.example.androidprojecttemplate.core.routes.AndroidTemplateRoutes
import com.example.androidprojecttemplate.feature_splash.application.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ScreenSplash(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val scale = remember {
        Animatable(0.0f)
    }
    LaunchedEffect(key1 = true) {
        viewModel.checkUserLoggedIn()
        viewModel.loginSharedFlow.collectLatest {
            delay(1000)
            val route = when (it) {
                true -> AndroidTemplateRoutes.Home.routeName
                false -> AndroidTemplateRoutes.Login.routeName
            }
            navController.navigate(route) {
                popUpTo(AndroidTemplateRoutes.Splash.routeName) {
                    inclusive = true
                }
            }
        }
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )


    }
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AndroidTemplateLogo()
        }
    }
}