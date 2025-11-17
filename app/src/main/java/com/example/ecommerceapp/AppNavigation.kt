package com.example.ecommerceapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.screen.AuthScreen
import com.example.ecommerceapp.screen.HomeScreen
import com.example.ecommerceapp.screen.LoginScreen
import com.example.ecommerceapp.screen.SignupScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier){
    val  navController = rememberNavController()

    NavHost(navController = navController,startDestination = "auth"){
        composable("auth"){
            AuthScreen(modifier,navController)
        }
        composable("login"){
            LoginScreen(modifier)
        }
        composable("signup"){
            SignupScreen(modifier,navController)
        }

        composable("homeScreen"){
            HomeScreen(modifier)
        }
    }


}