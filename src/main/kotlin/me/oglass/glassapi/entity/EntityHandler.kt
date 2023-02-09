package me.oglass.glassapi.entity

import org.bukkit.entity.LivingEntity

object EntityHandler {
    private val entities = HashMap<Int,BukkitLivingEntity>()
    fun registerEntity(entity: BukkitLivingEntity) {
        entities[entity.getHandle().entityId] = entity
    }
    fun getEntityByEntity(entity: LivingEntity): BukkitLivingEntity? {
        return entities[entity.entityId]
    }
    fun getEntityByID(id: Int): BukkitLivingEntity? {
        return entities[id]
    }
}