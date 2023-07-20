package com.example.starwarsapitool.presentation.utlis

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun <T> CustomAlertDialog(
    isDialogActive: MutableState<Boolean>,
    title: String,
    message: String,
    obj: T,
    onAcceptClickListener: (T) -> Unit
) {
    if (isDialogActive.value) {
        AlertDialog(
            onDismissRequest = {
                isDialogActive.value = false
            },
            title = {
                Text(title)
            },
            text = {
                Text(message)
            },
            confirmButton = {
                TextButton(onClick = {
                    onAcceptClickListener(obj)
                    isDialogActive.value = false
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    isDialogActive.value = false
                }) {
                    Text("Dismiss")
                }
            }
        )
    }
}