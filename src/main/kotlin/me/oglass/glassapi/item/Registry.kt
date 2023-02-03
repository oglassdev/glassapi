package me.oglass.glassapi.item

import de.tr7zw.changeme.nbtapi.NBTItem
import me.oglass.glassapi.GlassAPI
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object Registry {
    private var items: HashMap<String, Item>? = HashMap()
    fun init() {
        try {
            for (field in javaClass.fields) {
                val item = field.get(null) as Item
                items?.set(item.getID(), item)
            }
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
            items = null
        }
    }
    fun getItem(item: ItemStack?): Item? {
        if (items == null || item == null || item.type == Material.AIR || item.hasItemMeta()) return null
        val nbti = NBTItem(item)
        if (!nbti.hasTag(GlassAPI.getItemDataName())) return null
        val data = nbti.getCompound(GlassAPI.getItemDataName())
        return items?.getOrDefault(data.getString("ID"),null)
    }
    fun getItem(item: String): Item? {
        if (items == null) return null
        return items?.getOrDefault(item,null)
    }
}