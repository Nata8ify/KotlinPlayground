package database.sql

import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document
import java.util.*

val mongo : database.sql.Mongo = Mongo()
val collection = mongo.mongoDatabase.getCollection("jedi")

fun main(arsg : Array<String>){
    val doc : Document = Document("name", "Leia Skywalker").append("age", 28)
    //insertMany(500)
    seeAll()
    //collection.deleteMany(Document())
    //findOne(Document("random128Text", "EJAYOBYYOQKOSIWCWTKOSRHJHLCLAPWJSLDKZOYVOUIDWNYZAUALKGASABNKPZIBIPQFSINNRIDJQJLVWJCDCCBMJBZPAIJRPKZHDNEKFPGITTYPVSXLFOHQXJNEHOWXP"))
    //updateOne(Document("random128Text", "UPDATE"),  Document("\$set", Document("random128Text", "UPDATE_AGAIN").append("EKABM", "UPDATE").append("list", Arrays.asList(54, 2, "Ar", Date(System.currentTimeMillis())))))
}

fun insertMany(size : Int){
    collection.insertMany(mongo.randomGenerate(size))
}

fun seeAll(){
    for(doc in collection.find()){
        println(doc.toJson())
    }
}

fun seeCount(){
    println("Count : ${collection.count()}")
}

fun findOne(doc: Document){
    println("Result :  ${collection.find(doc).first().toJson()}")
}

fun updateOne(toEditDoc: Document, toUpdateDoc: Document){
    collection.updateOne(toEditDoc, toUpdateDoc)
}