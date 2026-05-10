package me.rohandacoder.nemux.data

import androidx.compose.runtime.mutableStateListOf

object ToolRepository {
    val tools = mutableStateListOf(
        Tool("calculator", "Calculator", "Basic and scientific calculations", "🧮"),
        Tool("notes", "Notes", "Quick note taking app", "📝"),
        Tool("unit_converter", "Unit Converter", "Convert between different units", "🔄"),
        Tool("stopwatch", "Stopwatch", "Precise timing with lap functionality", "⏱️"),
        Tool("qr_scanner", "QR Scanner", "Scan and generate QR codes", "📷"),
        Tool("color_picker", "Color Picker", "Pick and convert colors", "🎨"),
        Tool("battery_info", "Battery Info", "Detailed battery statistics", "🔋"),
        Tool("network_tools", "Network Tools", "Ping, traceroute, and more", "🌐"),
        Tool("file_manager", "File Manager", "Browse and manage files", "📁"),
        Tool("text_editor", "Text Editor", "Edit text files with syntax highlighting", "📄")
    )

    fun toggleFavorite(toolId: String) {
        val tool = tools.find { it.id == toolId }
        tool?.isFavorite = !(tool.isFavorite)
    }

    fun getFavoriteTools(): List<Tool> {
        return tools.filter { it.isFavorite }
    }

    fun getAllTools(): List<Tool> {
        return tools.toList()
    }
}
