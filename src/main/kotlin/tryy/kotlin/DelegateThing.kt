package tryy.kotlin

import kotlin.reflect.KProperty

class Ex {
    var d : String by Deleg()

}

class Deleg(){
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): String{
        return ""
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) {
        println("$value has been assigned to ${prop.name} in $thisRef")
    }
}

class Main{
    fun main(args: Array<String>){

    }
}