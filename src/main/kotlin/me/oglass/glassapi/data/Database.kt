package me.oglass.glassapi.data

interface Database<CollectionType,DocumentType> {
    fun getCollection(name: String): DatabaseCollection<CollectionType,DocumentType>
    fun closeConnection()
}
interface DatabaseCollection<CollectionType,DocumentType> {
    fun containsValue(id: String): Boolean
    fun getFirstDocument(id: String): DatabaseDocument<DocumentType>?
    fun getFirstDocument(key: String, value: Any?): DatabaseDocument<DocumentType>?
    fun getDocuments(): Array<DatabaseDocument<DocumentType>>
    fun getDocuments(key: String, value: Any?): Array<DatabaseDocument<DocumentType>>
    fun deleteDocument(id: String)
    fun getCollection(): CollectionType
}
abstract class DatabaseDocument<T> {
    fun getInt(key: String): Int? {
        val value = get(key)
        return if (value is Int) value else null
    }
    fun getDouble(key: String): Double? {
        val value = get(key)
        return if (value is Double) value else null
    }
    fun getString(key: String): String? {
        val value = get(key)
        return if (value is String) value else null
    }
    fun getBoolean(key: String): Boolean {
        val value = get(key)
        return if (value is Boolean) value else false
    }
    fun getFloat(key: String): Float? {
        val value = get(key)
        return if (value is Float) value else null
    }
    abstract fun get(key: String): Any?
    abstract fun set(key: String,value: Any)
    abstract fun getDocument(): T
}