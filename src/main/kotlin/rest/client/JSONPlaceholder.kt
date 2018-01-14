package rest.client

import retrofit2.Call
import retrofit2.http.GET

interface JSONPlaceholder {

    @GET("/posts")
    fun posts(): Call<List<Post>>
}