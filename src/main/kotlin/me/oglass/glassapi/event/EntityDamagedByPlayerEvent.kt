package me.oglass.glassapi.event

import me.oglass.glassapi.entity.BukkitLivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class EntityDamagedByPlayerEvent
    (private val player: Player, private val entity: BukkitLivingEntity,
     private val damageType: DamageType) : Event(), Cancellable {
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
    fun getEntity(): BukkitLivingEntity {
        return entity
    }
    fun getDamageType(): DamageType {
        return damageType
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