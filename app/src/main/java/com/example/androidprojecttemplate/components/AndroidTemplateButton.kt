package com.example.androidprojecttemplate.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidprojecttemplate.R

@Composable
fun AndroidTemplateButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = colorResource(R.color.button_color)
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