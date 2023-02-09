package me.oglass.glassapi.player

import me.oglass.glassapi.item.Registry
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.util.UUID

class PlayerHandler(plugin: JavaPlugin) : Listener {
    private val players = HashMap<UUID,PlayerWrapper>()
    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
        object : BukkitRunnable() {
            override fun run() {
                for (player in players.values) {
                    player.updateStats()
                }
            }
        }.runTaskTimer(plugin,0,5)
    }

    fun getPlayer(player: Player): PlayerWrapper {
        return players[player.uniqueId]!!
    }

    @EventHandler fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        if (players.containsKey(player.uniqueId)) return
        players[player.uniqueId] = PlayerWrapper(player)
    }
    @EventHandler fun onClick(event: PlayerInteractEvent) {
        val item = event.item
        if (item == null || item.type == Material.AIR || item.hasItemMeta()) return
        val i = Registry.getItem(item) ?: return
    }
    @EventHandler fun onLeave(event: PlayerQuitEvent) {
        getPlayer(event.player).saveData()
    }
}