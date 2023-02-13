package me.oglass.glassapi.player

import me.oglass.glassapi.item.ModifiedItem
import me.oglass.glassapi.modifier.Modifier
import me.oglass.glassapi.modifier.ModifierType
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot

class PlayerWrapper(val player: Player) {
    private val stats = HashMap<Modifier,Double>()
    private var items = HashMap<EquipmentSlot,ModifiedItem>()
    fun updateStats() {
        updateItems()
        for (modifier in Modifier.getModifiers()) {
            if (modifier.getType() != ModifierType.STAT) continue
            stats[modifier] = 0.0
        }
        for (item in items.values) {
            for (modifier in stats.keys) {
                stats[modifier] = (stats[modifier]?:0.0) + item.getTotalModifier(modifier)
                println(modifier.getName() + ": " + stats[modifier])
            }
        }
    }
    fun getStat(modifier: Modifier): Double {
        return stats.getOrDefault(modifier, 0.0)
    }
    fun getItems(): List<ModifiedItem> {
        return items.values.toList()
    }
    fun updateItems() {
        items.clear()
        val boots = player.equipment.boots
        if (boots != null && boots.type != Material.AIR && boots.hasItemMeta())
            items[EquipmentSlot.FEET] = ModifiedItem(boots)
        val leggings = player.equipment.leggings
        if (leggings != null && leggings.type != Material.AIR && leggings.hasItemMeta())
            items[EquipmentSlot.LEGS] = ModifiedItem(leggings)
        val chestplate = player.equipment.chestplate
        if (chestplate != null && chestplate.type != Material.AIR && chestplate.hasItemMeta())
            items[EquipmentSlot.CHEST] = ModifiedItem(chestplate)
        val helmet = player.equipment.helmet
        if (helmet != null && helmet.type != Material.AIR && helmet.hasItemMeta())
            items[EquipmentSlot.HEAD] = ModifiedItem(helmet)
        val mainHand = player.equipment.itemInHand
        if (mainHand != null && mainHand.type != Material.AIR && mainHand.hasItemMeta())
            items[EquipmentSlot.HAND] = ModifiedItem(mainHand)
    }
}