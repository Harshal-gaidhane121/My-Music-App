package com.example.my_music_app

import androidx.annotation.DrawableRes

data class Lib(@DrawableRes val icon:Int,val name:String)

val libraries= listOf<Lib>(
    Lib(R.drawable.music,"Playlist"),
    Lib(R.drawable.artists,"Artists"),
    Lib(R.drawable.album,"Album"),
    Lib(R.drawable.song,"Songs"),
    Lib(R.drawable.genre,"Genre")
)