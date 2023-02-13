package me.oglass.glassapi.player

import me.oglass.glassapi.item.AbilityType
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerToggleFlightEvent
import org.bukkit.event.player.PlayerToggleSneakEvent
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
                    try {
                        player.updateStats()
                    } catch (_: ConcurrentModificationException) {}
                }
            }
        }.runTaskTimerAsynchronously(plugin,0,1)
    }

    fun getPlayer(player: Player): PlayerWrapper {
        return players[player.uniqueId]!!
    }

    @EventHandler fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        if (players.containsKey(player.uniqueId)) return
        players[player.uniqueId] = PlayerWrapper(player)
        players[player.uniqueId]?.updateItems()
    }
    @EventHandler fun onLeave(event: PlayerQuitEvent) {
        players.remove(event.player.uniqueId)
    }
    @EventHandler fun onClick(event: PlayerInteractEvent) {
        val player = getPlayer(event.player)
        for (modItem in player.getItems()) {
            for (ability in modItem.baseItem.getAbilities()) {
                when (ability.getType()) {
                    AbilityType.CLICK -> {
                        if (event.action != Action.PHYSICAL) ability.invoke(player, modItem)
                    }
                    AbilityType.LEFT_CLICK -> {
                        if (event.action == Action.LEFT_CLICK_AIR ||
                            event.action == Action.LEFT_CLICK_BLOCK
                        ) ability.invoke(player,modItem)
                    }
                    AbilityType.RIGHT_CLICK -> {
                        if (event.action == Action.RIGHT_CLICK_AIR ||
                            event.action == Action.RIGHT_CLICK_BLOCK
                        ) ability.invoke(player,modItem)
                    }
                    else -> {}
                }
            }
        }
    }
    @EventHandler fun onFly(event: PlayerToggleFlightEvent) {
        val player = getPlayer(event.player)
        for (modItem in player.getItems()) {
            for (ability in modItem.baseItem.getAbilities()) {
                if (ability.getType() == AbilityType.FLY) ability.invoke(player,modItem)
            }
        }
    }
    @EventHandler fun onSneak(event: PlayerToggleSneakEvent) {
        val player = getPlayer(event.player)
        for (modItem in player.getItems()) {
            for (ability in modItem.baseItem.getAbilities()) {
                if (ability.getType() == AbilityType.SNEAK) ability.invoke(player,modItem)
            }
        }
    }
}