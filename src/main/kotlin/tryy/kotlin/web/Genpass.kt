package tryy.kotlin.web

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query


interface Genpass {

    companion object {
        const val baseUrl = "https://aqueous-thicket-56745.herokuapp.com/rest/"
    }

    @POST("${baseUrl}genalphabet")
    fun generateAlphabetic(
            @Query("length") length : Int = 6,
            @Query("amount") amount : Int = 1,
            @Query("flag") flag : Int = 1
    ) : Call<List<String>>
}