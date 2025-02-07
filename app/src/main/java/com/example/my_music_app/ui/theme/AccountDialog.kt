package com.example.my_music_app.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun AccountDialog(dialogOpen: MutableState<Boolean>){

    if(dialogOpen.value){
        AlertDialog(
            onDismissRequest = {
                dialogOpen.value=false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        dialogOpen.value=false
                    }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        dialogOpen.value=false
                    }
                ) {
                    Text("Dismiss")
                }
            },
            title = {
                Text("Add Account", modifier = Modifier.padding(bottom = 10.dp))
            },
            text = {
                Column (modifier = Modifier,){
                    TextField(value = "", onValueChange = {

                    }, label = {
                        Text("Email")
                    }, modifier = Modifier.padding(bottom = 10.dp))

                    TextField(value = "", onValueChange = {

                    }, label = {
                        Text("Password")
                    })
                }
            }, modifier = Modifier,
            shape = RoundedCornerShape(7.dp),
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }

}