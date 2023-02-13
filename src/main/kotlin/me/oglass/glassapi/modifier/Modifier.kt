package me.oglass.glassapi.modifier

import org.bukkit.plugin.java.JavaPlugin

/**
 * Don't initialize a Modifier more than once.
 * Every time a modifier is initialized, it registers the listeners
 */
abstract class Modifier {
    companion object {
        @JvmField
        val HEALTH = Health()
        @JvmField
        val DEFENSE = Defense()
        @JvmField
        val ENERGY = Energy()

        private var modifiers = HashMap<String,Modifier>()
        @JvmStatic
        fun getModifier(id: String): Modifier? {
            return modifiers.getOrDefault(id.uppercase(),null)
        }
        private fun registerModifier(modifier: Modifier) {
            if (modifiers.containsKey(modifier.getID().uppercase()))
                throw RuntimeException("Failed to register modifier " +
                        "${modifier.getID()} because it already exists!")
            modifiers[modifier.getID().uppercase()] = modifier
        }
        @JvmStatic
        fun getModifiers(): Collection<Modifier> {
            return modifiers.values
        }
    }
    init {
        registerModifier(this)
    }
    abstract fun getName(): String
    abstract fun getID(): String
    abstract fun getType(): ModifierType
    open fun getModifiedModifiers(level: Double): HashMap<Modifier,Double> {
        return hashMapOf()
    }
}
enum class ModifierType {
    ENCHANT,STAT,CUSTOM
}