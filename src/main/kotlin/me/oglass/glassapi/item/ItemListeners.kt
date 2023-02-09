package me.oglass.glassapi.item

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class ItemListeners : Listener {
    @EventHandler
    fun onClick(event: PlayerInteractEvent) {
        val item = event.item
        if (item == null || item.type == Material.AIR || item.hasItemMeta()) return
        val i = Registry.getItem(item) ?: return
    }
}