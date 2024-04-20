package com.example.androidprojecttemplate.feature_login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.crcs.transportation.components.AndroidTemplateSpacer
import com.crcs.transportation.components.AndroidTemplateTextButton
import com.example.androidprojecttemplate.R
import com.example.androidprojecttemplate.R.color.button_color
import com.example.androidprojecttemplate.R.string.signin_label
import com.example.androidprojecttemplate.components.AndroidTemplateLoadingProgressIndicator
import com.example.androidprojecttemplate.components.AndroidTemplateLogo
import com.example.androidprojecttemplate.components.AndroidTemplateText
import com.example.androidprojecttemplate.components.AndroidTemplateTextField
import com.example.androidprojecttemplate.core.routes.AndroidTemplateRoutes
import com.example.androidprojecttemplate.feature_login.application.LoginViewModel
import com.example.androidprojecttemplate.feature_login.domain.events.LoginAlertEvents
import com.example.androidprojecttemplate.feature_login.domain.events.LoginEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest


@Composable
fun ScreenLogin(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val emailIdState = viewModel.emailIdState.value
    val passwordState = viewModel.passwordState.value
    val requestStatusState = viewModel.requestStatus

    /// Post framework callback
    LaunchedEffect(key1 = true) {
        viewModel.loginAlertEventFlow.collectLatest { events ->
            when (events) {
                is LoginAlertEvents.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = events.message,
                        duration = SnackbarDuration.Short,
                    )
                    delay(100L)
                    navController.navigate(AndroidTemplateRoutes.Home.routeName) {
                        popUpTo(AndroidTemplateRoutes.Login.routeName) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) },) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AndroidTemplateLogo(sizeScale = 0.65)
            AndroidTemplateSpacer(height = 80.dp)
            AndroidTemplateTextField(
                value = emailIdState.data,
                hint = emailIdState.hint,
                isError = emailIdState.dataIsError,
                keyboardType = KeyboardType.Email,
                onChanged = {
                    viewModel.onEvent(LoginEvents.OnEmailIdInput(it))
                }
            )
            AndroidTemplateSpacer(height = 16.dp)
            AndroidTemplateTextField(
                value = passwordState.data,
                hint = passwordState.hint,
                isError = passwordState.dataIsError,
                keyboardType = KeyboardType.Password,
                onChanged = {
                    viewModel.onEvent(LoginEvents.OnPasswordInput(it))
                }
            )
            AndroidTemplateSpacer(height = 46.dp)
            AndroidTemplateButton(
                label = stringResource(signin_label),
                modifier = Modifier
                    .width(200.dp)
                    .height(36.dp),
                onClick = {
                    viewModel.onEvent(LoginEvents.OnSignInClicked)
                }
            )
        }
        AndroidTemplateLoadingProgressIndicator(
            state = requestStatusState,
            message = "Logging in ..."
        )
    }
}

@Composable
fun AndroidTemplateButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: ButtonColors = ButtonDefaults.buttonColors(
        contentColor = colorResource(button_color)
    )
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        colors = color,
    ) {
        AndroidTemplateText(
            text = label,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center
        )
    }
}
