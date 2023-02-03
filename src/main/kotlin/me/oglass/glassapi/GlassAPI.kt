package me.oglass.glassapi

class GlassAPI(val itemDataName: String) {
    companion object {
        private var itemDataName = ""
        fun getItemDataName(): String {
            return itemDataName
        }
    }
    init {
        GlassAPI.itemDataName = itemDataName
    }
}