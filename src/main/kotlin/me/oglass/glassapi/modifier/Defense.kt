package me.oglass.glassapi.modifier

class Defense : Modifier() {
    override fun getName(): String {
        return "Defense"
    }
    override fun getID(): String {
        return "GLASSAPI_DEFENSE"
    }
    override fun getType(): ModifierType {
        return ModifierType.STAT
    }
}