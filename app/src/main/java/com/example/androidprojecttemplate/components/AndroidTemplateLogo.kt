package com.example.androidprojecttemplate.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androidprojecttemplate.R

@Composable
fun AndroidTemplateLogo(
    sizeScale: Double = 1.0,
) {
    val size = 200 * sizeScale
    Box(
        modifier = Modifier
            .width(size.dp)
            .height(size.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.android),
            contentDescription = "CRCS Logo",
        )
    }

}