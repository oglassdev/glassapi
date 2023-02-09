package me.oglass.glassapi.item

import de.tr7zw.changeme.nbtapi.NBT
import de.tr7zw.changeme.nbtapi.NBTItem
import me.oglass.glassapi.GlassAPI
import me.oglass.glassapi.modifier.Modifier
import org.bukkit.inventory.ItemStack

fun ModifiedItem(item: Item): ModifiedItem {
    return ModifiedItem(item.getItem())
}
class ModifiedItem constructor(private val item: ItemStack) {
    val baseItem: Item
    private val modifiers = HashMap<Modifier,Double>()
    init {
        val data = NBTItem(item).getCompound(GlassAPI.getItemDataName())
        baseItem = Registry.getItem(data.getString("ID"))!!
        if (data.hasTag("MODIFIERS")) {
            val compound = data.getCompound("MODIFIERS")
            for (key in compound.keys) {
                modifiers[Modifier.getModifier(key.toString())!!] = compound.getDouble(key)
            }
        }
    }
    fun resetItem() {
        item.itemMeta = baseItem.getItem().itemMeta
    }
    fun getModifier(modifier: Modifier): Double {
        return modifiers.getOrDefault(modifier,0.0)
    }
    fun getTotalModifier(modifier: Modifier): Double {
        return getModifier(modifier) + (baseItem.getDefaultModifiers()?.getOrDefault(modifier,0.0) ?: 0.0)
    }
    fun setModifier(modifier: Modifier, amount: Double) {
        modifiers[modifier] = amount
    }
    fun updateItem() {
        NBT.modify(item, fun (item) {
            val data = (item as NBTItem).getCompound(GlassAPI.getItemDataName())
            if (modifiers.size > 0) {
                val mods = data.getCompound("MODIFIERS")
                modifiers.forEach(fun (modifier,value) {
                    mods.setDouble(modifier.getName(),value)
                })
            }
        })
    }
}