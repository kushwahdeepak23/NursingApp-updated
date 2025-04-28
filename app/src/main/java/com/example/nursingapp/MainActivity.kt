package com.example.nursingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
//import androidx.wear.compose.material.Text
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            Surface(color = MaterialTheme.colorScheme.background) {
                NavHost(navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen(onLoginSuccess = {
                            navController.navigate("dashboard")
                        })
                    }
                    composable("dashboard") {
                        DashboardScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun DashboardScreen() {
    Text("Welcome to Dashboard!", style = MaterialTheme.typography.headlineMedium)
}
