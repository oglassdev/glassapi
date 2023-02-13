package me.oglass.glassapi.event

import me.oglass.glassapi.item.Ability
import me.oglass.glassapi.item.ModifiedItem
import me.oglass.glassapi.player.PlayerWrapper
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class AbilityUseEvent(val player: PlayerWrapper,val ability: Ability,
                      val item: ModifiedItem?) : Event(),Cancellable {
    companion object {
        private val handlers = HandlerList()
        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }
    private var cancelled = false
    override fun isCancelled(): Boolean {
        return cancelled
    }
    override fun setCancelled(cancelled: Boolean) {
        this.cancelled = cancelled
    }
    override fun getHandlers(): HandlerList {
        return getHandlerList()
    }
}