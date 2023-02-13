package me.oglass.glassapi.item

import de.tr7zw.changeme.nbtapi.NBT
import de.tr7zw.changeme.nbtapi.NBTItem
import me.oglass.glassapi.GlassAPI
import me.oglass.glassapi.modifier.Modifier
import me.oglass.glassapi.util.Chat
import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack
import java.util.logging.Level

class ModifiedItem constructor(val bukkitItem: ItemStack) {
    val baseItem: Item
    private val modifiers = HashMap<Modifier,Double>()
    init {
        val data = NBTItem(bukkitItem).getCompound(GlassAPI.getItemDataName())
        baseItem = Registry.getItem(data.getString("ID"))!!
        if (data.hasTag("MODIFIERS")) {
            val compound = data.getCompound("MODIFIERS")
            for (key in compound.keys) {
                val modifier = Modifier.getModifier(key.toString())
                if (modifier == null) {
                    Chat.sendLog(Level.WARNING, "Failed to find modifier $key")
                    continue
                }
                modifiers[modifier] = compound.getDouble(key)
            }
        }
    }
    fun resetItem() {
        updateItem()
    }
    fun getModifier(modifier: Modifier): Double {
        return modifiers.getOrDefault(modifier,0.0)
    }
    fun getTotalModifier(modifier: Modifier): Double {
        return getModifier(modifier) +
                (baseItem.getDefaultModifiers()?.
                getOrDefault(modifier,0.0) ?: 0.0)
    }
    fun setModifier(modifier: Modifier, amount: Double) {
        modifiers[modifier] = amount
    }
    fun updateItem() {
        NBT.modify(bukkitItem, fun (item) {
            val data = (item as NBTItem).getCompound(GlassAPI.getItemDataName())
            if (modifiers.size > 0) {
                val mods = data.getCompound("MODIFIERS")
                modifiers.forEach(fun (modifier,value) {
                    mods.setDouble(modifier.getID(),value)
                })
            }
        })
        val meta = bukkitItem.itemMeta
        meta.lore = GlassAPI.getItemFormatter().formatLore(this)
        meta.displayName = GlassAPI.getItemFormatter().formatName(this)
        bukkitItem.itemMeta = meta
    }
}