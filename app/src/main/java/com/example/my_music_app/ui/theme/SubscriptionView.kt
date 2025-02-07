package com.example.my_music_app.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SubscriptionView(){
    Card(
        modifier = Modifier.fillMaxWidth().
        padding(10.dp),
        elevation = 10.dp
        ) {

        Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {

            Row(){
                Column() {
                    Text("Musical")
                    Text("Free Tier")
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                TextButton(onClick = { }) {
                    Text("See All Plans")
                }
            }

            Divider()

            Row(modifier = Modifier.padding(top =20.dp, bottom = 10.dp)) {
                Icon(imageVector = Icons.Default.AccountBox,
                    contentDescription = null)
                Text("Get a Plan")
            }

        }

    }
}