package me.oglass.glassapi

import me.oglass.glassapi.format.ChatFormatter
import me.oglass.glassapi.format.ItemFormatter
import me.oglass.glassapi.modifier.Health
import me.oglass.glassapi.modifier.Modifier
import me.oglass.glassapi.player.PlayerHandler
import org.bukkit.plugin.java.JavaPlugin

class GlassAPI(val plugin: JavaPlugin, val itemDataName: String) {
    companion object {
        private var plugin: JavaPlugin? = null
        private var playerHandler: PlayerHandler? = null;
        private var itemDataName = "GlassAPI"
        private var itemFormatter = ItemFormatter()
        private var chatFormatter = ChatFormatter()
        fun getPlugin(): JavaPlugin {
            return plugin!!
        }
        fun getItemDataName(): String {
            return itemDataName
        }
        fun getPlayerHandler(): PlayerHandler {
            return playerHandler!!
        }
        fun setItemFormatter(formatter: ItemFormatter) {
            itemFormatter = formatter
        }
        fun getItemFormatter(): ItemFormatter {
            return itemFormatter
        }
        fun setChatFormatter(formatter: ChatFormatter) {
            chatFormatter = formatter
        }
        fun getChatFormatter(): ChatFormatter {
            return chatFormatter
        }
    }
    init {
        GlassAPI.plugin = plugin
        GlassAPI.itemDataName = itemDataName
        playerHandler = PlayerHandler(plugin)
    }
}