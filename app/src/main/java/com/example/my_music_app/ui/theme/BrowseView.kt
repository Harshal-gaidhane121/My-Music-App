package com.example.my_music_app.ui.theme

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import com.example.my_music_app.R

@Composable
fun Browse(){

    val categories= listOf("Hits","Happy","workout","Running","Yoga")
    LazyVerticalGrid(GridCells.Fixed(2)) {
        items(categories){
            cat->
            BrowserItem(cat=cat, drawable = R.drawable.ic_launcher_foreground)
        }
    }

}