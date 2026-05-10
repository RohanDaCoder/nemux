package me.rohandacoder.nemux.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.rohandacoder.nemux.components.ToolCard
import me.rohandacoder.nemux.data.ToolRepository

@Composable
fun FavoritesScreen(
    onNavigateBack: () -> Unit,
    onToolClick: (String) -> Unit
) {
    var favoriteTools by remember { mutableStateOf(ToolRepository.getFavoriteTools()) }
    
    // Observe changes to tools
    val allTools = ToolRepository.getAllTools()
    favoriteTools = allTools.filter { it.isFavorite }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favorites") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            androidx.compose.material.icons.Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (favoriteTools.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Column(
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "⭐",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No favorites yet",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Tap the heart icon on any tool to add it here",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(favoriteTools, key = { it.id }) { tool ->
                    ToolCard(
                        tool = tool,
                        onToggleFavorite = { 
                            ToolRepository.toggleFavorite(tool.id)
                        },
                        onClick = { onToolClick(tool.id) }
                    )
                }
            }
        }
    }
}
