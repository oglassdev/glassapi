package me.oglass.glassapi.item

import de.tr7zw.changeme.nbtapi.NBT
import de.tr7zw.changeme.nbtapi.NBTItem
import me.oglass.glassapi.GlassAPI
import me.oglass.glassapi.modifier.Modifier
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

abstract class Item(plugin: JavaPlugin) {
    abstract fun getName(): String
    abstract fun getMaterial(): Material
    abstract fun getID(): String
    abstract fun getDescription(): Array<String>?
    abstract fun getDefaultModifiers(): HashMap<Modifier,Double>?
    abstract fun getAbilities(): Array<Ability>
    fun getItem(): ItemStack {
        val item = ItemStack(getMaterial())
        NBT.modify(item, fun(rwnbt) {
            val nbti: NBTItem = rwnbt as NBTItem
            val compound = nbti.getOrCreateCompound(GlassAPI.getItemDataName())
            compound.setString("ID",getID())
        })
        return item
    }
}