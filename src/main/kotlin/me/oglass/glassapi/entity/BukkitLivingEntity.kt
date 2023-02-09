package me.oglass.glassapi.entity

import me.oglass.glassapi.util.EntityUtil
import me.oglass.glassapi.util.RNG
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.ItemStack

open class BukkitLivingEntity(private val type: EntityType) {
    private var helmet: ItemStack? = null
    private var chestplate: ItemStack? = null
    private var leggings: ItemStack? = null
    private var boots: ItemStack? = null
    private var hand: ItemStack? = null

    private var ai = true
    private var gravity = true
    private var invulnerable = false

    private var drops: RNG = RNG()
    private var handle: LivingEntity? = null
    fun spawn(location: Location) {
        if (handle != null) return
        handle = location.world.spawnEntity(Location(location.world,0.0,0.0,0.0),type) as LivingEntity
        val equipment = handle!!.equipment
        equipment.helmet = helmet
        equipment.chestplate = chestplate
        equipment.leggings = leggings
        equipment.boots = boots
        equipment.itemInHand = hand
        EntityUtil.setAI(handle!!,ai)
        EntityUtil.setGravity(handle!!,gravity)
        EntityUtil.setInvulnerable(handle!!,invulnerable)
        setLocation(location)
    }
    fun setHelmet(stack: ItemStack) {
        helmet = stack
        if (handle != null) handle!!.equipment.helmet = stack
    }
    fun setChestplate(stack: ItemStack) {
        chestplate = stack
        if (handle != null) handle!!.equipment.chestplate = stack
    }
    fun setLeggings(stack: ItemStack) {
        leggings = stack
        if (handle != null) handle!!.equipment.leggings = stack
    }
    fun setBoots(stack: ItemStack) {
        boots = stack
        if (handle != null) handle!!.equipment.boots = stack
    }
    fun setItemInHand(stack: ItemStack) {
        hand = stack
        if (handle != null) handle!!.equipment.itemInHand = stack
    }

    fun setAI(value: Boolean) {
        ai = value
        if (handle != null) EntityUtil.setAI(handle!!,value)
    }
    fun setGravity(value: Boolean) {
        gravity = value
        if (handle != null) EntityUtil.setGravity(handle!!,value)
    }
    fun setInvulnerable(value: Boolean) {
        invulnerable = value
        if (handle != null) EntityUtil.setInvulnerable(handle!!,value)
    }
    fun setLocation(location: Location) {
        if (handle != null) EntityUtil.setLocation(handle!!,location.x,location.y,location.z,location.yaw,location.pitch)
    }

    fun setDrops(drops: RNG) {
        this.drops = drops
    }
    fun getHandle(): LivingEntity {
        if (handle == null) throw RuntimeException("Cannot get handle if entity has not been spawned in.")
        return handle!!
    }
}