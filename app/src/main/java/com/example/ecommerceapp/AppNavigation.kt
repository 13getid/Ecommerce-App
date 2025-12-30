package com.example.ecommerceapp


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.screen.AuthScreen
import com.example.ecommerceapp.screen.HomeScreen
import com.example.ecommerceapp.screen.LoginScreen
import com.example.ecommerceapp.screen.SignupScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    // 1. Use a State variable to hold the start destination safely
    var startDestination by remember { mutableStateOf("loading") }

    // 2. Observe the auth state asynchronously when the composable is launched
    LaunchedEffect(Unit) {
        val currentUser = Firebase.auth.currentUser
        startDestination = if (currentUser != null) "home" else "auth"
    }

    // 3. Only show the NavHost when the destination is determined (not "loading")
    if (startDestination != "loading") {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable("auth") {
                AuthScreen(modifier, navController)
            }
            composable("login") {
                LoginScreen(modifier, navController)
            }
            composable("signup") {
                SignupScreen(modifier, navController)
            }
            composable("home") {
                HomeScreen(modifier, navController)
            }
        }
    } else {
        // Optional: Show a loading screen while auth state is checked
        // Text("Loading...")
    }
}
