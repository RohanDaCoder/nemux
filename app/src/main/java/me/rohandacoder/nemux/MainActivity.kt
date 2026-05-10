package me.rohandacoder.nemux

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.rohandacoder.nemux.ui.screens.ToolsScreen
import me.rohandacoder.nemux.ui.screens.FavoritesScreen
import me.rohandacoder.nemux.ui.screens.AboutScreen
import me.rohandacoder.nemux.ui.theme.NemuxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NemuxTheme(
                darkTheme = true // Force dark mode by default
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NemuxApp()
                }
            }
        }
    }
}

@Composable
fun NemuxApp() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "tools"
    ) {
        composable("tools") {
            ToolsScreen(
                onNavigateToAbout = { navController.navigate("about") },
                onNavigateToFavorites = { navController.navigate("favorites") },
                onToolClick = { toolId -> 
                    // TODO: Navigate to tool detail screen when implemented
                }
            )
        }
        
        composable("favorites") {
            FavoritesScreen(
                onNavigateBack = { navController.popBackStack() },
                onToolClick = { toolId ->
                    // TODO: Navigate to tool detail screen when implemented
                }
            )
        }
        
        composable("about") {
            AboutScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
