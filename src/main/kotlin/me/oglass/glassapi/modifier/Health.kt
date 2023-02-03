package me.oglass.glassapi.modifier

import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin

class Health(plugin: JavaPlugin) : Modifier(plugin) {
    override fun getType(): ModifierType {
        return ModifierType.STAT
    }

    override fun onInteract(interaction: PlayerInteractEvent) {
        println(this)
    }
}