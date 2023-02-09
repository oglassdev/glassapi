package me.oglass.glassapi.item

import org.bukkit.event.Listener

abstract class Ability : Listener {
    abstract fun getName(): String
    abstract fun run(item: ModifiedItem?)
    abstract fun getType(): AbilityType
    abstract fun getDescription(): Array<String>
    fun getItemDescription(): Array<String> {
        return getDescription()
    }
}
enum class AbilityType {
    SNEAK,LEFT_CLICK,RIGHT_CLICK,CLICK,FLY
}