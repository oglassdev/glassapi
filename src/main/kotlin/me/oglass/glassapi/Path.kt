package me.oglass.glassapi

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.scheduler.BukkitRunnable
import org.spigotmc.event.entity.EntityDismountEvent
import java.lang.reflect.InvocationTargetException

class Path(private val player: Player, private val world: World, vararg pathLocations: PathLocation) : Listener {
    private val locations: ArrayList<PathLocation> = ArrayList(pathLocations.asList())
    private val modifier = 0.4
    private var stand: ArmorStand? = null

    fun start() {
        Bukkit.getPluginManager().registerEvents(this, GlassAPI.getPlugin())
        val loc = locations[0]
        stand = world.spawnEntity(Location(world, loc.x, loc.y + 0.6, loc.z), EntityType.ARMOR_STAND) as ArmorStand
        stand!!.isVisible = false
        stand!!.isMarker = true
        stand!!.setGravity(false)
        stand!!.passenger = player
        slowMove()
    }

    fun end() {
        stand!!.remove()
    }

    fun slowMove() {
        if (stand == null) return
        val loc1 = locations[0]
        val loc2 = locations[1]
        locations.removeAt(0)
        val time = loc1.time
        val entLoc = stand!!.location
        setLocation(stand!!, loc1.x, loc1.y + modifier, loc1.z)
        object : BukkitRunnable() {
            var i: Long = 0
            override fun run() {
                if (stand!!.isDead || !player.isOnline) {
                    stand!!.remove()
                    cancel()
                    return
                }
                if (i >= time) {
                    setLocation(stand!!, loc2.x, loc2.y + modifier, loc2.z)
                    if (locations.size <= 1) {
                        Bukkit.getScheduler().runTaskLater(GlassAPI.getPlugin(), {
                            stand!!.remove()
                            stand = null
                        }, 3)
                    } else slowMove()
                    cancel()
                    return
                }
                val x = entLoc.x + (loc2.x - entLoc.x) * ((i + 0.0) / time)
                val y = entLoc.y - modifier + (loc2.y - (entLoc.y - modifier)) * ((i + 0.0) / time)
                val z = entLoc.z + (loc2.z - entLoc.z) * ((i + 0.0) / time)
                setLocation(stand!!, x, y + modifier, z)
                i++
            }
        }.runTaskTimer(GlassAPI.getPlugin(), 0, 0)
    }
    @EventHandler
    fun dismountEvent(e: EntityDismountEvent) {
        if (e.entity != player) return
        if (e.dismounted != stand) return
        Bukkit.getScheduler().runTaskLater(GlassAPI.getPlugin(), { stand!!.passenger = player }, 1)
    }

    companion object {
        private fun setLocation(stand: ArmorStand, x: Double, y: Double, z: Double) {
            try {
                val getHandleMethod = stand.javaClass.getMethod("getHandle")
                val handle = getHandleMethod.invoke(stand)
                val setLocationMethod = handle.javaClass.getMethod(
                    "setLocation",
                    Double::class.javaPrimitiveType,
                    Double::class.javaPrimitiveType,
                    Double::class.javaPrimitiveType,
                    Float::class.javaPrimitiveType,
                    Float::class.javaPrimitiveType
                )
                setLocationMethod.invoke(handle, x, y, z, 0f, 0f)
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }
}
class PathLocation(val x: Double, val y: Double, val z: Double, val time: Int)