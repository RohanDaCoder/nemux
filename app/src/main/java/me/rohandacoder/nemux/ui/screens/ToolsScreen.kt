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
fun ToolsScreen(
    onNavigateToAbout: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    onToolClick: (String) -> Unit
) {
    val allTools by remember { mutableStateOf(ToolRepository.getAllTools()) }
    val favoriteTools = allTools.filter { it.isFavorite }
    val otherTools = allTools.filter { !it.isFavorite }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nemux") },
                actions = {
                    IconButton(onClick = onNavigateToFavorites) {
                        BadgeBox(badgeCount = favoriteTools.size) {
                            Icon(
                                androidx.compose.material.icons.Icons.Filled.Favorite,
                                contentDescription = "Favorites"
                            )
                        }
                    }
                    IconButton(onClick = onNavigateToAbout) {
                        Icon(
                            androidx.compose.material.icons.Icons.Filled.Info,
                            contentDescription = "About"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            // Favorites Section (if any)
            if (favoriteTools.isNotEmpty()) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        Text(
                            text = "⭐ Favorites",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                
                items(favoriteTools, key = { it.id }) { tool ->
                    ToolCard(
                        tool = tool,
                        onToggleFavorite = { 
                            ToolRepository.toggleFavorite(tool.id)
                        },
                        onClick = { onToolClick(tool.id) }
                    )
                }
                
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            
            // All Tools Section
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Text(
                        text = "🔧 All Tools",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "${allTools.size} tools",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            items(otherTools, key = { it.id }) { tool ->
                ToolCard(
                    tool = tool,
                    onToggleFavorite = { 
                        ToolRepository.toggleFavorite(tool.id)
                    },
                    onClick = { onToolClick(tool.id) }
                )
            }
            
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun BadgeBox(
    badgeCount: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        content()
        if (badgeCount > 0) {
            Surface(
                modifier = Modifier
                    .offset(x = 8.dp, y = (-4).dp)
                    .size(16.dp),
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.error
            ) {
                Box(contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Text(
                        text = if (badgeCount > 9) "9+" else badgeCount.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onError
                    )
                }
            }
        }
    }
}
