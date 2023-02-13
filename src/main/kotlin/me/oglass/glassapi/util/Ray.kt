package me.oglass.glassapi.util

import org.bukkit.Effect
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Entity
import java.util.*

object Ray {
    /**
     * The higher, the faster, but less accurate.
     * The lower, the slower, but more accurate.
     */
    private const val ACCURACY = 0.3f
    private val ALLOWED_BLOCKS = listOf(
        Material.AIR,
        Material.WATER, Material.STATIONARY_WATER,
        Material.LAVA, Material.STATIONARY_LAVA,
        Material.LONG_GRASS, Material.DEAD_BUSH, Material.DOUBLE_PLANT,
        Material.RED_ROSE, Material.YELLOW_FLOWER
    )

    /**
     * Returns all entities within the line of sight. Stops at block
     * @param location Location. Must have a direction vector to work properly.
     * @param range How far to cast the "ray".
     * @return Entities within the line of sight.
     */
    fun getLineOfSight(location: Location, range: Int): Array<Entity> {
        val entities: MutableList<Entity> = ArrayList()
        var d = 0.0
        while (d <= range) {
            location.add(location.direction.multiply(ACCURACY))
            if (!ALLOWED_BLOCKS.contains(location.block.type)) break
            for (entity in location.world.getNearbyEntities(
                location,
                (ACCURACY / 2).toDouble(),
                (ACCURACY / 2).toDouble(),
                (ACCURACY / 2).toDouble()
            )) {
                if (!entities.contains(entity)) entities.add(entity)
            }
            d += ACCURACY.toDouble()
        }
        return entities.toTypedArray()
    }

    /**
     * Returns all entities within the line of sight. Stops at block
     * @param location Location. Must have a direction vector to work properly.
     * @param range How far to cast the "ray".
     * @param particle The particle to play every iteration.
     * @return Entities within the line of sight.
     */
    fun getLineOfSight(location: Location, range: Int, particle: Effect?): Array<Entity> {
        val entities: MutableList<Entity> = ArrayList()
        var d = 0.0
        while (d <= range) {
            location.add(location.direction.multiply(ACCURACY))
            if (!ALLOWED_BLOCKS.contains(location.block.type)) break
            location.world.playEffect(location, particle, 1)
            for (entity in location.world.getNearbyEntities(
                location,
                (ACCURACY / 2).toDouble(),
                (ACCURACY / 2).toDouble(),
                (ACCURACY / 2).toDouble()
            )) {
                if (!entities.contains(entity)) entities.add(entity)
            }
            d += ACCURACY.toDouble()
        }
        return entities.toTypedArray()
    }
}