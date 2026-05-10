package me.rohandacoder.nemux.data

data class Tool(
    val id: String,
    val name: String,
    val description: String,
    val icon: String = "🔧",
    var isFavorite: Boolean = false
)
