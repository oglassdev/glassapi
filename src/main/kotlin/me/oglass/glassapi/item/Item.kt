package me.oglass.glassapi.item

import de.tr7zw.changeme.nbtapi.NBT
import de.tr7zw.changeme.nbtapi.NBTItem
import me.oglass.glassapi.GlassAPI
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

abstract class Item {
    abstract fun getName(): String
    abstract fun getMaterial(): Material
    abstract fun getID(): String
    abstract fun getDescription(): Array<String>?
    abstract fun getDefaultModifiers(): HashMap<Modifier,Double>?
    fun getItem(): ItemStack {
        var item = ItemStack(getMaterial())
        NBT.modify(item, fun(rwnbt) {
            val nbti: NBTItem = rwnbt as NBTItem
            nbti.getOrCreateCompound(GlassAPI.getItemDataName())
        })
        return item
    }
}
enum class Modifier(val formattedName: String, val color: ChatColor,
                    val onInit: () -> Unit = {}) {
    Health("Health",ChatColor.RED);
    companion object {
        private var initialized = false;
        fun init() {
            if (initialized) return
            for (modifier in values()) {
                modifier.onInit.invoke()
            }
            initialized = true
        }
    }
}

object MetaGen {
    fun generateLore(meta: ItemStack): ItemMeta {
        val list = mutableListOf<String>()

        return meta.itemMeta
    }
}