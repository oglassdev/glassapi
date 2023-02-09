package me.oglass.glassapi.player

import me.oglass.glassapi.item.ModifiedItem
import me.oglass.glassapi.modifier.Modifier
import me.oglass.glassapi.modifier.ModifierType
import org.bukkit.Material
import org.bukkit.entity.Player

class PlayerWrapper(val player: Player) {
    private val stats = HashMap<Modifier,Double>()
    fun updateStats() {
        for (modifier in Modifier.getModifiers()) {
            if (modifier.getType() != ModifierType.STAT) continue
            stats[modifier] = 0.0
        }
        val boots = player.equipment.boots
        if (boots != null && boots.type != Material.AIR && boots.hasItemMeta()) {
            val modItem = ModifiedItem(boots)
            for (modifier in stats.keys) {
                modItem.getTotalModifier(modifier)
            }
        }
        val leggings = player.equipment.boots
        if (leggings != null && leggings.type != Material.AIR && leggings.hasItemMeta()) {
            val modItem = ModifiedItem(leggings)
            for (modifier in stats.keys) {
                modItem.getTotalModifier(modifier)
            }
        }
        val chestplate = player.equipment.boots
        if (chestplate != null && chestplate.type != Material.AIR && chestplate.hasItemMeta()) {
            val modItem = ModifiedItem(chestplate)
            for (modifier in stats.keys) {
                modItem.getTotalModifier(modifier)
            }
        }
        val helmet = player.equipment.boots
        if (helmet != null && helmet.type != Material.AIR && helmet.hasItemMeta()) {
            val modItem = ModifiedItem(helmet)
            for (modifier in stats.keys) {
                modItem.getTotalModifier(modifier)
            }
        }
        val mainHand = player.equipment.itemInHand
        if (mainHand != null && mainHand.type != Material.AIR && mainHand.hasItemMeta()) {
            val modItem = ModifiedItem(mainHand)
            for (modifier in stats.keys) {
                modItem.getTotalModifier(modifier)
            }
        }
    }
    fun saveData() {

    }
}