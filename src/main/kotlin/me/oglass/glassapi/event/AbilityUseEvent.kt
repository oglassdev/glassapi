package me.oglass.glassapi.event

import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class AbilityUseEvent(private val player: Player) : Event(),Cancellable {
    companion object {
        private val handlers = HandlerList()
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }
    private var cancelled = false;
    fun getPlayer(): Player {
        return player
    }
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