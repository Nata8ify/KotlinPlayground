package tryy.kotlin


/* Reference -> http://kotlinlang.org/docs/reference/typecasts.html#unsafe-cast-operator */
fun main(args: Array<String>) {
    val numberOfLife: Int = 42
    var beginningIsTheWord: String? = numberOfLife as? String //Safe Casting (Return 'null' if cast-casting is mismatched)
    println("Safe Cast Int -> String : $beginningIsTheWord")

    try {
        var wordMadeTheWorld: String? = numberOfLife as String //Unsafe Casting
        println("Unsafe Cast Int -> String : $wordMadeTheWorld")
    } catch (e : Exception){
        println("Unsafe Cast Int -> String Failed : ${e.message}")
    }

}
