package me.oglass.glassapi.entity

import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

class TestEntity : BukkitLivingEntity(EntityType.ZOMBIE) {
    init {
        setBoots(ItemStack(Material.DIAMOND_BOOTS))
        setAI(false)
    }
}