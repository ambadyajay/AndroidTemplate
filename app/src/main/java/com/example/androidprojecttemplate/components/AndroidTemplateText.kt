package com.example.androidprojecttemplate.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.example.androidprojecttemplate.R.color.label_color

@Composable
fun AndroidTemplateText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit,
    color: Color = colorResource(
        label_color
    ),
    fontWeight: FontWeight = FontWeight.W400,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        fontWeight = fontWeight,
        textAlign = textAlign,
        modifier = modifier,
    )
}