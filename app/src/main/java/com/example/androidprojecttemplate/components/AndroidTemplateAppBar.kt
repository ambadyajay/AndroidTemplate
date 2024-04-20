package com.example.androidprojecttemplate.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androidprojecttemplate.core.theme.fontPoppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AndroidTemplateAppBar(title: String, navController: NavController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            })
            {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Goto previous page",
                )
            }
        },
        title = {
            Text(
                text = title,
                fontFamily = fontPoppins,
                textAlign = TextAlign.Center,
                fontSize = 17.sp,
                fontWeight = FontWeight.W400,
                color = Color(0xFF08215B),
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor =Color.White,)
    )
}