package tryy.kotlin.web

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface Raw{
    companion object {
        const val GOOGLE : String = "https://www.google.co.th/?gws_rd=ssl"
    }

    @GET("/")
    fun rawGoogle() : Call<ResponseBody>
}