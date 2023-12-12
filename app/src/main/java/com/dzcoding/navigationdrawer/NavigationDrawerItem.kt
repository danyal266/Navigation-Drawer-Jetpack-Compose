package com.dzcoding.navigationdrawer

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationDrawerItem(
    val title:String,
    val selectedIcon:ImageVector,
    val unSelectedIcon:ImageVector,
    val badgeCount:Int?
)
