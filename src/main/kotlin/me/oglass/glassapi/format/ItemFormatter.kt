package me.oglass.glassapi.format

import me.oglass.glassapi.item.ModifiedItem
import me.oglass.glassapi.modifier.Modifier
import me.oglass.glassapi.modifier.ModifierType

class ItemFormatter {
    fun formatName(item: ModifiedItem): String {
        return item.baseItem.getName()
    }
    fun formatLore(item: ModifiedItem): List<String> {
        val list = arrayListOf<String>()
        for (modifier in Modifier.getModifiers()) {
            if (modifier.getType() == ModifierType.STAT)
                list.add("${modifier.getName()}: ${item.getTotalModifier(modifier)}")
        }
        if (item.baseItem.getDescription() != null) {
            list.add("")
            list.addAll(item.baseItem.getDescription()!!)
        }
        return list
    }
}