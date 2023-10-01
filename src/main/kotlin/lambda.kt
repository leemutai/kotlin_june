fun main() {
    val coins: (Int) -> String = { quantity -> "$quantity quarters" }

    val treatFunction = trickOrTreat(false, coins)
    val trickFunction = trickOrTreat(true, null)
    treatFunction()
    trickFunction()
}

fun trickOrTreat(isTrick: Boolean, extraTreat: ((Int) -> String)?): () -> Unit {
    if (isTrick) {
        return trick
    } else {
        return {
            println(extraTreat?.invoke(5) ?: "No extra treats!")
        }
    }
}

val trick = {
    println("No treats!")
}

val treat = {
    println("Have a treat!")
}
