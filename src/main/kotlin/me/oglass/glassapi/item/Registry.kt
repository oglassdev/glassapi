package me.oglass.glassapi.item

import de.tr7zw.changeme.nbtapi.NBTItem
import me.oglass.glassapi.GlassAPI
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object Registry {
    private var items: HashMap<String, Item> = HashMap()
    @JvmStatic
    fun registerItem(item: Item) {
        items[item.getID()] = item
    }
    @JvmStatic
    fun getItem(item: ItemStack?): Item? {
        if (item == null || item.type == Material.AIR || item.hasItemMeta()) return null
        val nbti = NBTItem(item)
        if (!nbti.hasTag(GlassAPI.getItemDataName())) return null
        val data = nbti.getCompound(GlassAPI.getItemDataName())
        return items.getOrDefault(data.getString("ID"),null)
    }
    @JvmStatic
    fun getItem(item: String): Item? {
        return items.getOrDefault(item,null)
    }
}