package com.example.my_music_app.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.my_music_app.MainViewModel
import com.example.my_music_app.Screen
import com.example.my_music_app.screenInDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.ui.unit.sp

import com.example.my_music_app.R
import com.example.my_music_app.screenInBottom

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainView(){

    val viewModel: MainViewModel= viewModel()
    val currentScreen=remember{
        viewModel.currentScreen.value
    }

    val dialogOpen=remember{
        mutableStateOf(false)
    }
    val isSheetFullScreen by remember {
        mutableStateOf(false)
    }
    val modalSheetState= rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {
            it != ModalBottomSheetValue.HalfExpanded
        }
    )

    val roundedCornerRadius=if(isSheetFullScreen) 0.dp else 12.dp

    val modifier=if(isSheetFullScreen)Modifier.fillMaxSize() else Modifier.fillMaxWidth()
    var title by remember { mutableStateOf(Screen.DrawerScreen.Account.dTitle) }
    val scaffoldState: ScaffoldState= rememberScaffoldState()
    val scope: CoroutineScope= rememberCoroutineScope()

    // Allow us to find out on which "view" we currently on
    val controller: NavController= rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute=navBackStackEntry?.destination?.route

    val bottomBar: @Composable () ->  Unit = {
         if(currentScreen is Screen.DrawerScreen || currentScreen== Screen.BottomScreen.Home){
             BottomNavigation(
                 backgroundColor = Color(0, 194, 111, 255),
                 modifier = Modifier.height(70.dp)
                 ) {
                 screenInBottom.forEach{
                     item->
                     val isSelected = currentRoute == item.bRoute
                     val tint = if(isSelected) Color.White else Color.Black
                     BottomNavigationItem(
                         selected = currentRoute == item.bRoute,
                         onClick = {
                             title=item.btitle
                             controller.navigate(item.bRoute)
                                   },
                         icon = {
                             Icon(painter = painterResource(id=item.icon),contentDescription = null,tint=tint)
                                },
                         label = {Text(item.btitle, color =tint)},
                         selectedContentColor = Color.White,
                         unselectedContentColor = Color.Black,
                     )
                 }
             }
         }
    }


    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(roundedCornerRadius),
        sheetContent = {
        MoreBottomSheet(modifier=modifier)
    }) {
        Scaffold (
            bottomBar=bottomBar,
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars),
            scaffoldState = scaffoldState,
            topBar = {
                androidx.compose.material.TopAppBar(
                    actions = {
                        androidx.compose.material.IconButton(
                            onClick = {
                                scope.launch {
                                    if(modalSheetState.isVisible)modalSheetState.hide()
                                    else modalSheetState.show()
                                }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.MoreVert,
                                contentDescription = null)
                        }
                    },
                    title = { Text(title) },
                    navigationIcon = { IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }){
                        Icon(imageVector = Icons.Default.AccountCircle,
                            contentDescription = null
                        )
                    } },
                    backgroundColor = Color(0, 194, 111, 255),
                    contentColor = Color.White
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
                                dialogOpen.value=true
                            }else{
                                controller.navigate(item.dRoute)
                                title=item.dTitle
                            }
                        }



                    }
                }
            }

        ){
            Navigation(
                navController = controller,
                viewModel = viewModel,
                pd = it
            )

            AccountDialog(dialogOpen)
        }
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

@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel,pd:PaddingValues){

    NavHost(navController = navController as NavHostController,
        startDestination = Screen.DrawerScreen.Account.route,
        modifier = Modifier.padding(pd)
        ){
        composable(Screen.BottomScreen.Home.bRoute){
            Home()
        }
        composable(Screen.BottomScreen.Browse.bRoute){
            Browse()
        }
        composable(Screen.BottomScreen.Library.bRoute){
            Library()
        }
        composable(Screen.DrawerScreen.Account.route){
                AccountView()
        }
        composable(Screen.DrawerScreen.Subscription.route){
                SubscriptionView()
        }
    }

}

@Composable
fun MoreBottomSheet(modifier: Modifier){

    Box(modifier=modifier.fillMaxWidth().height(300.dp).background(Color(98, 194, 106, 255))){
        Column() {
            Row(modifier=Modifier.padding(20.dp)){
               Icon(imageVector = Icons.Default.Settings,
                   contentDescription = null
               )
                Text(text = "Settings",modifier=Modifier.padding(horizontal = 10.dp), fontSize = 20.sp)
            }
            Row(modifier=Modifier.padding(20.dp)){
                Icon(imageVector = Icons.Default.Share,
                    contentDescription = null
                )
                Text(text = "Share",modifier=Modifier.padding(horizontal = 10.dp), fontSize = 20.sp)
            }
            Row(modifier=Modifier.padding(20.dp)){
                Icon(imageVector = Icons.Default.Info,
                    contentDescription = null
                )
                Text(text = "Help",modifier=Modifier.padding(horizontal = 10.dp), fontSize = 20.sp)
            }
        }
    }

}