package me.oglass.glassapi.event

import me.oglass.glassapi.entity.BukkitLivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PlayerDamagedByEntityEvent
    (val player: Player, val entity: BukkitLivingEntity,
     val damageType: DamageType) : Event(), Cancellable {
    companion object {
        private val handlers = HandlerList()
        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }
    private var cancelled = false;
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