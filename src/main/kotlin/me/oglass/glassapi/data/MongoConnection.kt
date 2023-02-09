package me.oglass.glassapi.data

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.*
import org.bson.Document
import java.util.*
import kotlin.collections.ArrayList

class MongoConnection(connectionStr: String) : Database<MongoCollection<Document>,Document> {
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
    override fun getCollection(name: String): DatabaseCollection<MongoCollection<Document>,Document> {
        return MongoDBCollection(database.getCollection(name))
    }
    override fun closeConnection() {
        client.close()
    }
}
class MongoDBCollection(private val collection: MongoCollection<Document>) : DatabaseCollection<MongoCollection<Document>,Document> {
    override fun containsValue(id: String): Boolean {
        val iterable = collection
            .find(Document("_id", id))
        return iterable.first() != null
    }
    override fun getFirstDocument(id: String): DatabaseDocument<Document> {
        return getFirstDocument("_id", id)
    }
    override fun getFirstDocument(key: String, value: Any?): DatabaseDocument<Document> {
        return getDocuments(key, value).first()
    }

    override fun getDocuments(): Array<DatabaseDocument<Document>> {
        val i = collection.find().iterator()
        val list = ArrayList<DatabaseDocument<Document>>()
        while (i.hasNext()) {
            list.add(MongoDBDocument(i.next()))
        }
        return list.toArray(arrayOf())
    }

    override fun getDocuments(key: String, value: Any?): Array<DatabaseDocument<Document>> {
        val i = collection.find(Document(key,value)).iterator()
        val list = ArrayList<DatabaseDocument<Document>>()
        while (i.hasNext()) {
            list.add(MongoDBDocument(i.next()))
        }
        return list.toArray(arrayOf())
    }
    override fun deleteDocument(id: String) {
        if (!containsValue(id)) return
        collection.deleteOne(Document("_id", id))
    }
    override fun getCollection(): MongoCollection<Document> {
        return collection
    }
}
class MongoDBDocument(private val document: Document) : DatabaseDocument<Document>() {
    override fun get(key: String): Any? {
        return if (document.containsKey(key)) document[key] else null
    }
    override fun set(key: String, value: Any) {
        document[key] = value
    }
    override fun getDocument(): Document {
        return document
    }
}