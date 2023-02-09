package me.oglass.glassapi.modifier

import org.bukkit.plugin.java.JavaPlugin

/**
 * Don't initialize a Modifier more than once.
 * Every time a modifier is initialized, it registers the listeners
 */
abstract class Modifier(plugin: JavaPlugin) {
    companion object {
        private var modifiers = HashMap<String,Modifier>()
        fun <T : Modifier> getModifier(type: Class<T>): T? {
            return modifiers.getOrDefault(type.typeName,null) as T?
        }
        fun getModifier(name: String): Modifier? {
            return modifiers.getOrDefault(name,null)
        }
        fun registerModifier(modifier: Modifier) {
            modifiers[modifier.javaClass.typeName] = modifier
        }
    }
    abstract fun getName(): String
    abstract fun getType(): ModifierType
}
enum class ModifierType {
    ENCHANT,STAT,CUSTOM
}