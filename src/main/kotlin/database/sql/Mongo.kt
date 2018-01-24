package database.sql

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoDatabase
import org.bson.Document

class Jedi(val name: String , val age: Int)
class Mongo{
    val mongoURI : MongoClientURI by lazy {
        MongoClientURI("mongodb+srv://default:kogy9i8u@sample-f84xa.mongodb.net/test")
    }

    val mongoClient : MongoClient by lazy {
        MongoClient(mongoURI)
    }

    val mongoDatabase : MongoDatabase by lazy {
        mongoClient.getDatabase("sample")
    }

    fun randomGenerate(sampleAmount : Int) : List<Document>{
        val docs = ArrayList<Document>()
        for(i in 0..sampleAmount){
            docs.add(Document("random128Text", randomWords(128)).append(randomWords(4), randomWords(5)))
        }
        return docs
    }

    fun randomWords(length: Int): String{
        val strBuilder : StringBuilder = StringBuilder("")
        for (i in 0..length){
            strBuilder.append((Math.random()*(90 - 65 + 1) + 65).toInt().toChar().toString())
        }
        return strBuilder.toString()
    }
}