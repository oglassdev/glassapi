package me.oglass.glassapi.util

import de.tr7zw.changeme.nbtapi.NBT
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import java.lang.reflect.InvocationTargetException

object EntityUtil {
    fun setGravity(entity: Entity, value: Boolean) {
        NBT.modify(entity) { nbt: ReadWriteNBT -> nbt.setByte("NoGravity",(if (value) 0 else 1).toByte()) }
    }
    fun setInvulnerable(entity: Entity, value: Boolean) {
        NBT.modify(entity) { nbt: ReadWriteNBT ->
            run {
                if (!value) nbt.removeKey("Invulnerable")
                else nbt.setByte("Invulnerable", 1.toByte())
            }
        }
    }
    fun setAI(entity: Entity, value: Boolean) {
        NBT.modify(entity) { nbt: ReadWriteNBT -> nbt.setByte("NoAI",(if (value) 0 else 1).toByte()) }
    }
    fun setLocation(entity: LivingEntity, x: Double, y: Double, z: Double, yaw: Float, pitch: Float) {
        try {
            val getHandleMethod = entity.javaClass.getMethod("getHandle")
            val handle = getHandleMethod.invoke(entity)
            val setLocationMethod = handle.javaClass.getMethod(
                "setLocation",
                Double::class.javaPrimitiveType,
                Double::class.javaPrimitiveType,
                Double::class.javaPrimitiveType,
                Float::class.javaPrimitiveType,
                Float::class.javaPrimitiveType
            )
            setLocationMethod.invoke(handle, x, y, z, yaw, pitch)
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
}