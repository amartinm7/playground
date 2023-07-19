package lessons.language

class Collections {
    // https://kotlinlang.org/docs/collection-parts.html
    fun printNumbers() {
        val numbers = arrayOf<String>("lss","mddd","gffffff","vgggg")
        // create a map with the key as the values of the array and the map value as the transform function it.length
        println(numbers.associateWith { it.length })
        // create a map with the key as the function it.length and the map value the values of the initial array
        println(numbers.associateBy { it.length })
        // create a map with the key as the function it.first().uppercaseChar() and the map value the function it.length
        println(numbers.associateBy(keySelector = { it.first().uppercaseChar() }, valueTransform = { it.length }))
    }
}

fun main (args: Array<String>){
    Collections().printNumbers()
}