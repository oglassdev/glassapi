package me.oglass.glassapi.format

import org.bukkit.ChatColor

class ChatFormatter {
    fun formatColors(string: String): String {
        return ChatColor.translateAlternateColorCodes('&',string)
    }
}