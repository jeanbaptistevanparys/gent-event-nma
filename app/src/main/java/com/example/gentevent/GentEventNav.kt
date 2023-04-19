package com.example.gentevent

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gentevent.ui.screens.*

sealed class TopNavigationScreens(val route: String) {
    object Home : TopNavigationScreens("home")
    object Friends : TopNavigationScreens("friends")
    object Settings : TopNavigationScreens("settings")
}

sealed class OtherScreens(val route: String) {
    object DetailEvent : OtherScreens("detail_event")
    object DetailFriends : OtherScreens("detail_friends")
}

//val uiState = UiState()

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TopNavigationScreens.Home.route) {
        composable(TopNavigationScreens.Home.route) {
            MainScreen(navController = navController)
        }
        composable(TopNavigationScreens.Friends.route) {
            FriendsScreen(navController = navController)
        }
        composable(TopNavigationScreens.Settings.route) {
            SettingsScreen(navController = navController)
        }
        composable(OtherScreens.DetailEvent.route) {
            DetailEventScreen(navController = navController)
        }
        composable(OtherScreens.DetailFriends.route) {
            DetailFriendsScreen(navController = navController)
        }
    }

}


