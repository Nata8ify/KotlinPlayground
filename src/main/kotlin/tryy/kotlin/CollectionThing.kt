package tryy.kotlin

/*val randomize : List<Password>? by lazy {
    val list = ArrayList<Password>()
    val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(Genpass.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    val genpassService : Genpass = retrofit.create(Genpass::class.java)
    var response = genpassService.generateAlphabetic(6, 10, 1).enqueue(object : Callback<List<Password>>{
        override fun onFailure(call: Call<List<Password>>?, t: Throwable?) {
            println(call?.request().toString())
        }

        override fun onResponse(call: Call<List<Password>>?, response: Response<List<Password>>?) {
            list.addAll(response!!.body()!!.toList())
            println(list)
        }
    })
    list
    *//*if(response.isSuccessful){
        response.body()
    } else {
        null
    }*//*
}*/

fun main(args: Array<String>) {
    println(List<Int>(100){
        1
    }) /* Declare List with members of 1 for 100*/

    var init : Int = 1
    repeat(100, {
        init = init.plus(it)
        println(init)
    })

   val numberMap = mapOf<Int, String>().map {
       1 to 1.toString()
       2 to 2.toString()
       3 to 3.toString()
   }
    println(numberMap)
}