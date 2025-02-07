package com.example.my_music_app.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.my_music_app.R

@Composable
fun AccountView(){

    Column(
        modifier = Modifier.fillMaxSize().padding(15.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Row {
                Icon(imageVector = Icons.Default.Person,
                    contentDescription = "Profile")

                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text("Harshal Gaidane")
                    Text("@harshal")
                }
            }


            IconButton(onClick = {

            }) {
                Icon(imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null)
            }

        }

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ){

          Icon(
              painter = painterResource(id = R.drawable.music),
              contentDescription = null)

            Text("My Music", modifier =Modifier.padding(10.dp))

        }

        Divider()


    }

}