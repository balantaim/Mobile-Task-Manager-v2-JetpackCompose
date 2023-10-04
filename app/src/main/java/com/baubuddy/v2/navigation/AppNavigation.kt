package com.baubuddy.v2.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.baubuddy.v2.view.details.DetailsScreen
import com.baubuddy.v2.view.home.HomeScreen
import com.baubuddy.v2.view.search.SearchScreen
import com.baubuddy.v2.view.splash.SplashScreen
import com.baubuddy.v2.viewmodel.DetailsViewModel
import com.baubuddy.v2.viewmodel.MainViewModel
import com.baubuddy.v2.viewmodel.SearchViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun AppNavigation (){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.name ){
        composable(AppScreens.SplashScreen.name){
            SplashScreen(navController = navController)
        }
        composable(AppScreens.HomeScreen.name){
            val mainViewModel = hiltViewModel<MainViewModel>()
            HomeScreen(navController = navController)
        }
        composable(route = AppScreens.DetailsScreen.name +"/{id}", arguments = listOf(
            navArgument("id"){ type = NavType.StringType; defaultValue = "" }
        )){backStackEntry ->
            val detailsViewModel = hiltViewModel<DetailsViewModel>()
            DetailsScreen(navController = navController, detailsViewModel, backStackEntry.arguments?.getString("id"))
        }
        composable(AppScreens.SearchScreen.name){
            val searchViewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(navController = navController)
        }
    }
}