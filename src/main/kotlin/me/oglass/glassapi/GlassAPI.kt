package me.oglass.glassapi

import org.bukkit.plugin.java.JavaPlugin

class GlassAPI(val plugin: JavaPlugin, val itemDataName: String) {
    companion object {
        private var plugin: JavaPlugin? = null
        private var itemDataName = "GlassAPI"
        fun getPlugin(): JavaPlugin {
            return plugin!!
        }
        fun getItemDataName(): String {
            return itemDataName
        }
    }
    init {
        GlassAPI.plugin = plugin
        GlassAPI.itemDataName = itemDataName
    }
}