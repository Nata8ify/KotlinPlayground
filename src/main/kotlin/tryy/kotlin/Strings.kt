package tryy.kotlin

/* http://kotlinlang.org/docs/reference/basic-types.html#string-literals */
fun main(args: Array<String>) {
    val escapedString : String = "\n\t*******\n\tEscaped\n\t*******\n"
    println(escapedString)

    val notAnEscapedString : String = """\n\t*******\n\tEscaped\n\t*******"""
    println(notAnEscapedString)

    val text = """
    | Tell me and I forget.
    | Teach me and I remember.
    | Involve me and I learn.
    | (Benjamin Franklin)
    """.trimMargin("/")
    println(text)

    println(printMementoMori())
}

fun printMementoMori() : String  {
    val memento : String = "Memento"
    val mori : String = "Mori"
    val mementoMori : String = """\d{7} \d{4}"""
    return "$mementoMori"
}