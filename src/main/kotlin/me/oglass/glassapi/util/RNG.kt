package me.oglass.glassapi.util

import org.bukkit.inventory.ItemStack

class RNG {
    private val drops = HashMap<ItemStack,Float>()
    fun addDrop(item: ItemStack, chance: Float): RNG {
        drops[item] = chance
        return this
    }
    fun roll(): Array<ItemStack> {
        val rolledDrops = arrayListOf(drops.keys)
        drops.forEach(fun (item,chance) {
            if (Math.random() > chance) rolledDrops.remove(mutableSetOf(item))
        })
        return rolledDrops.toArray(arrayOf())
    }
}