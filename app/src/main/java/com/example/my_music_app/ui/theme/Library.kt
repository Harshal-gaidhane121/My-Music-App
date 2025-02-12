package com.example.my_music_app.ui.theme

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.my_music_app.Lib
import com.example.my_music_app.libraries

@Composable
fun Library(){
    LazyColumn {
        items(libraries){
            lib->
            LibItem(lib=lib)
        }
    }
}

@Composable
fun LibItem(lib: Lib){

        Row (
            modifier= Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
            ){
            Row(){
                Icon(contentDescription = lib.name,
                    painter = painterResource(id=lib.icon),
                    modifier = Modifier.padding(10.dp)
                )
                Text(text=lib.name,
                    modifier = Modifier.padding(vertical = 10.dp))
            }
            Icon(imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.padding(10.dp)
                )
        }
    Divider(color = Color.LightGray)

}