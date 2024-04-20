package com.example.androidprojecttemplate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidprojecttemplate.core.networking.request_status.RequestStatus
import com.example.androidprojecttemplate.core.theme.primaryOrange

@Preview
@Composable
fun AndroidTemplateLoadingProgressIndicator(
    state: State<RequestStatus> = mutableStateOf(RequestStatus.Idle),
    message: String? = null
) {
    if (state.value == RequestStatus.Loading)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White.copy(alpha = 0.75F)),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CircularProgressIndicator(
                    color = primaryOrange
                )
                Spacer(modifier = Modifier.height(10.dp))
                if (!message.isNullOrBlank())
                    Text(text = message)
            }

        }
}
