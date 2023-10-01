 fun main() {
     val x = 20
     when (x) {
         2,3,5,7 -> println("X is a prime number between 1 and 10")
         in 1..10 -> println("x is a number between 1 and 10 but not a prime number")
         is Int -> println("x is an integer number, but not between 1 and 10")
         //2 -> println("X is a prime number between 1 and 10")
         //3 -> println("X is a prime number between 1 and 10")
         //5 -> println("X is a prime number between 1 and 10")
         //7 -> println("X is a prime number between 1 and 10")
         else -> println("X is not a prime number between 1 and 10")

     }
 }