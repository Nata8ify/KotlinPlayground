package database.sql

import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document

fun main(arsg : Array<String>){
    val mongo : database.sql.Mongo = Mongo()
    val dbCol = mongo.mongoDatabase.getCollection("jedi")
    val doc : Document = Document("name", "Anakin Skywalker").append("age", 28)
    dbCol.insertOne(doc)
    println(dbCol.find().first().toJson())
}