package me.oglass.glassapi.modifier

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

/**
 * Don't initialize a Modifier more than once.
 * Every time a modifier is initialized, it registers the listeners
 */
abstract class Modifier(plugin: JavaPlugin) : Listener {
    init {
        Bukkit.getPluginManager().registerEvents(this,plugin)
        object : BukkitRunnable() {
            override fun run() {
                onTick(this)
            }
        }.runTaskTimer(plugin,1,1)
    }
    abstract fun getType(): ModifierType
    open fun onTick(runnable: BukkitRunnable) {}
    @EventHandler
    open fun onInteract(interaction: PlayerInteractEvent) {}
}
enum class ModifierType {
    ENCHANT,STAT
}