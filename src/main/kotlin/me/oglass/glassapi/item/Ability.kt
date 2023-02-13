package me.oglass.glassapi.item

import me.oglass.glassapi.event.AbilityUseEvent
import me.oglass.glassapi.player.PlayerWrapper
import org.bukkit.Bukkit
import org.bukkit.event.Listener

abstract class Ability : Listener {
    abstract fun getName(): String
    abstract fun run(player: PlayerWrapper,event: AbilityUseEvent)
    fun invoke(player: PlayerWrapper, item: ModifiedItem?) {
        val event = AbilityUseEvent(player,this,item)
        Bukkit.getPluginManager().callEvent(event)
        run(player,event)
    }
    abstract fun getType(): AbilityType
    abstract fun getDescription(): Array<String>
    fun getItemDescription(): Array<String> {
        return getDescription()
    }
}
enum class AbilityType {
    SNEAK,LEFT_CLICK,RIGHT_CLICK,CLICK,FLY
}