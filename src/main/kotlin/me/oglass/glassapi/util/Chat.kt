package me.oglass.glassapi.util

import me.oglass.glassapi.GlassAPI
import org.bukkit.Bukkit
import java.util.logging.Level

object Chat {
    @JvmStatic
    fun sendLog(level: Level, message: String) {
        Bukkit.getLogger().log(level,"[${GlassAPI.getPlugin().name}] $message")
    }
}