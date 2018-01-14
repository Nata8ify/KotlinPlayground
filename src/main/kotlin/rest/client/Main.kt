package rest.client

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit : Retrofit by lazy {
     Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
             .addConverterFactory(GsonConverterFactory.create())
             .build()

}
val postPlaceholder : JSONPlaceholder by lazy {
    retrofit.create(JSONPlaceholder::class.java)
}

fun main(args: Array<String>){
    postPlaceholder.posts().enqueue(object : Callback<List<Post>>{
        override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
            //TODO()
        }

        override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {
            response?.body()?.forEach { post ->
                println(post.toString())
                println("-------")
            }
        }

    })
    /*.forEach { post ->
        print(post.toString())
    }*/
}