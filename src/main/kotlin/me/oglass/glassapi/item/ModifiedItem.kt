package me.oglass.glassapi.item

import de.tr7zw.changeme.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

fun ModifiedItem(item: Item): ModifiedItem {
    val stack = ItemStack(Material.AIR)
    return ModifiedItem(stack)
}
class ModifiedItem internal constructor(private val item: ItemStack) {
    private var baseItem: Item
    init {
        val data = NBTItem(item).getCompound(Item.dataKey)
        baseItem = Registry.getItem(data.getString("id"))!!
    }
    fun resetItem() {
    }
}