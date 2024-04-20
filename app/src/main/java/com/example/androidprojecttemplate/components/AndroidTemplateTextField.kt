package com.example.androidprojecttemplate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidprojecttemplate.R

@Composable
fun AndroidTemplateTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    onChanged: (String) -> Unit,
    isError: Boolean,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardActions: KeyboardActions = KeyboardActions(),
    labelColor: Color = colorResource(R.color.text_input_label_color)
) {
    Column {
        TextField(
            value = value,
            onValueChange = onChanged,
            modifier = modifier
                .background(
                    color = Color.White
                )
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            label = {
                Text(
                    text = hint,
                    fontSize = 16.sp,
                    color = labelColor
                )
            },
            trailingIcon = trailingIcon,
            keyboardActions = keyboardActions,
            isError = isError,
            maxLines = 1,
        )

        if (isError)
            ErrorText(hint = hint)
    }

}

@Composable
fun ErrorText(hint: String) {
    Text(
        text = "Invalid $hint",
        style = TextStyle(
            color = Color.Red,
            fontSize = 12.sp,

            )
    )
}