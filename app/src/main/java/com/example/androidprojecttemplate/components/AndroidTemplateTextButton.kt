package com.crcs.transportation.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.example.androidprojecttemplate.R

@Composable
fun AndroidTemplateTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    fontSize: TextUnit,
    color: Color = colorResource(
        R.color.label_color
    ),
    fontWeight: FontWeight = FontWeight.W400,
    textAlign: TextAlign = TextAlign.Center,
) {
    TextButton(onClick = onClick) {
        Text(
            text = text,
            fontSize = fontSize,
            color = color,
            fontWeight = fontWeight,
            textAlign = textAlign,
            modifier = modifier,

            )
    }
}