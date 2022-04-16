package com.example.noteapp.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text: String,
    Label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
    ){

    val keyBoardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent),
        maxLines = maxLine,
        label = {Text(Label)},
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Default),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyBoardController?.hide()
        }),
        modifier = modifier
    )

}


@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onCLick: () -> Unit,
    enabled: Boolean = true
){
    Button(
        onClick = onCLick,
        shape = CircleShape,
        enabled = enabled,
        modifier = modifier) {

        Text(text)

    }
    
}



















