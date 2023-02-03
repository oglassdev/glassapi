package me.oglass.glassapi.item

import org.bukkit.Material

class TestItem : Item() {
    override fun getName(): String {
        return "Test Item"
    }

    override fun getMaterial(): Material {
        return Material.ANVIL
    }

    override fun getID(): String {
        return "TEST_ITEM"
    }

    override fun getDescription(): Array<String> {
        return arrayOf("This is a test", "item, used for", "testing purposes.")
    }

    override fun getDefaultModifiers(): HashMap<Modifier, Double> {
        return hashMapOf(Modifier.Health to 12.0)
    }
}