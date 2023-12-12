package com.dzcoding.navigationdrawer


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dzcoding.navigationdrawer.ui.theme.NavigationDrawerTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDrawerTheme {
                val item = listOf(
                    NavigationDrawerItem(
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unSelectedIcon = Icons.Outlined.Home,
                        badgeCount = null
                    ),
                    NavigationDrawerItem(
                        title = "Info",
                        selectedIcon = Icons.Filled.Info,
                        unSelectedIcon = Icons.Outlined.Info,
                        badgeCount = 90
                    ),
                    NavigationDrawerItem(
                        title = "Settings",
                        selectedIcon = Icons.Filled.Settings,
                        unSelectedIcon = Icons.Outlined.Settings,
                        badgeCount = null
                    )
                )
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                var selectedItemIndex by rememberSaveable{
                    mutableIntStateOf(0)
                }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DismissibleNavigationDrawer(
                        drawerContent = {
                           DismissibleDrawerSheet {
                               Spacer(modifier = Modifier.padding(16.dp))
                               item.forEachIndexed { index, navigationDrawerItem ->

                                   NavigationDrawerItem(
                                       label = {
                                           Text(navigationDrawerItem.title)
                                       },
                                       onClick = {
                                           selectedItemIndex = index
                                           scope.launch {
                                               drawerState.close()
                                           }
                                       },
                                       selected = selectedItemIndex==index,
                                       icon = {
                                           Icon(
                                               imageVector = if (selectedItemIndex==index){
                                                   navigationDrawerItem.selectedIcon
                                               }else navigationDrawerItem.unSelectedIcon,
                                               contentDescription = navigationDrawerItem.title
                                           )
                                       },
                                       badge = {
                                           navigationDrawerItem.badgeCount?.let {
                                               Text(navigationDrawerItem.badgeCount.toString())
                                           }
                                       },
                                       modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                   )
                               }
                           }
                        },
                       drawerState = drawerState
                    ){
                           Scaffold (
                               topBar = {
                                   TopAppBar(
                                       title = {
                                           Text("NavigationDrawer Example")
                                       },
                                       navigationIcon = {
                                           IconButton(onClick = {
                                               scope.launch {
                                                   drawerState.open()
                                               }
                                           }){
                                               Icon(imageVector = Icons.Default.Menu,
                                                   contentDescription = null)
                                           }
                                       }

                                   )
                               }
                           ){  }
                    }
                }
            }
        }

    }
}

