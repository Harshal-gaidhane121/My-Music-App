package com.example.my_music_app.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.my_music_app.Screen
import com.example.my_music_app.screenInDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(){

    var title by remember { mutableStateOf("") }
    val scaffoldState: ScaffoldState= rememberScaffoldState()
    val scope: CoroutineScope= rememberCoroutineScope()

    // Allow us to find out on which "view" we currently on
    val controller: NavController= rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute=navBackStackEntry?.destination?.route

    Scaffold (

        topBar = {
            TopAppBar(title = { Text("Home") },
                navigationIcon = { IconButton(onClick = {
                    //open the drawer
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }){
                    Icon(imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                        )
                } }
                )
        },
        drawerContent={
            LazyColumn(Modifier.padding(16.dp)) {
                items(screenInDrawer){
                    item->
                        DrawerItem(selected =currentRoute==item.dRoute ,item=item) {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                            if(item.dRoute=="add_account"){
                                //open dialog
                            }else{
                                controller.navigate(item.dRoute)
                                title=item.dTitle
                            }
                        }



                }
            }
        }

    ){
        Text("hello", modifier = Modifier.padding(it))
    }
}

@Composable
fun DrawerItem(
    selected: Boolean,
    item:Screen.DrawerScreen,
    onDrawerItemClicked: ()-> Unit
){

    val background=if(selected) Color.LightGray else Color.White

    Row(
        modifier = Modifier.fillMaxWidth().
        padding(horizontal = 8.dp, vertical = 16.dp).
        background(background).
        clickable { onDrawerItemClicked() }

    ) {

        Icon(modifier = Modifier.padding(end = 8.dp,top=4.dp),
            painter = painterResource(id=item.icon),
            contentDescription = item.dTitle
            )
        Text(
            text = item.dTitle,
        )

    }

}