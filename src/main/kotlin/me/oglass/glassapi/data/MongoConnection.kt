package me.oglass.glassapi.data

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.*
import org.bson.Document
import org.bson.conversions.Bson
import java.util.*

class MongoConnection(connectionStr: String, val prefix: String) {
    private val client: MongoClient
    private val database: MongoDatabase
    init {
        val connectionString = ConnectionString(connectionStr)
        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        client = MongoClients.create(settings)
        database = client.getDatabase(Objects.requireNonNull(connectionString.database))
    }
    private fun getCollection(name: String): CollectionWrapper {
        return CollectionWrapper(database.getCollection(name))
    }
    fun closeConnection() {
        client.close() // A C D A - MULTIPLE CHOICE
    }
}
class CollectionWrapper(private val collection: MongoCollection<Document>) {
    fun containsValue(id: String): Boolean {
        val iterable = collection
            .find(Document("_id", id))
        return iterable.first() != null
    }
    fun getFirstDocument(id: String): Document? {
        return getFirstDocument("_id", id)
    }
    fun getFirstDocument(key: String, value: Any?): Document? {
        return getDocuments(key, value).first()
    }
    fun getDocuments(key: String, value: Any?): FindIterable<Document?> {
        return collection.find(Document(key, value))
    }
    fun deleteDocument(id: String) {
        if (!containsValue(id)) return
        collection.deleteOne(Document("_id", id))
    }
    fun deleteDocuments(filter: Bson) {
        collection.deleteMany(filter)
    }
    fun getCollection(): MongoCollection<Document> {
        return collection
    }
}