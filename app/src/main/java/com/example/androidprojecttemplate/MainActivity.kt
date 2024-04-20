package com.example.androidprojecttemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidprojecttemplate.core.routes.AndroidTemplateRoutes
import com.example.androidprojecttemplate.core.theme.AndroidProjectTemplateTheme
import com.example.androidprojecttemplate.feature_home.presentation.ScreenHome
import com.example.androidprojecttemplate.feature_login.presentation.ScreenLogin
import com.example.androidprojecttemplate.feature_splash.presentation.ScreenSplash
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidProjectTemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = AndroidTemplateRoutes.Splash.routeName,
                    ) {
                        /// splash route
                        composable(route = AndroidTemplateRoutes.Splash.routeName) {
                            ScreenSplash(navController)
                        }
                        /// login route
                        composable(route = AndroidTemplateRoutes.Login.routeName) {
                            ScreenLogin(navController)
                        }
                        /// home route
                        composable(route = AndroidTemplateRoutes.Home.routeName) {
                            ScreenHome(navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidProjectTemplateTheme {
        Greeting("Android")
    }
}