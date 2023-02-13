package me.oglass.glassapi.modifier

class Energy : Modifier() {
    override fun getName(): String {
        return "Energy"
    }
    override fun getID(): String {
        return "GLASSAPI_ENERGY"
    }
    override fun getType(): ModifierType {
        return ModifierType.STAT
    }
}