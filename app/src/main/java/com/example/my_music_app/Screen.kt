package com.example.my_music_app

import androidx.annotation.DrawableRes

sealed class Screen(val title: String,val route: String) {

    sealed class DrawerScreen(val dTitle:String,
                              val dRoute:String,
                              @DrawableRes val icon:Int)
        : Screen(dTitle,dRoute){
    object Account: DrawerScreen(
        "Account",
        "account",
        R.drawable.account
    )
    object Subscription:DrawerScreen(
        "Subscription",
        "subscribe",
        R.drawable.subscribe
    )
        object AddAccount:DrawerScreen(
            "Add Account",
            "add_account",
            R.drawable.person
        )

    }

}

val screenInDrawer= listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount
)