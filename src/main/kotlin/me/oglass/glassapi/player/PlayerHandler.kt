package me.oglass.glassapi.player

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class PlayerHandler(plugin: JavaPlugin) : Listener {
    init {
        Bukkit.getPluginManager().registerEvents(this,plugin)
    }
}