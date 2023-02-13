package me.oglass.glassapi.modifier

class Health : Modifier() {
    override fun getName(): String {
        return "Health"
    }
    override fun getID(): String {
        return "GLASSAPI_HEALTH"
    }
    override fun getType(): ModifierType {
        return ModifierType.STAT
    }
}