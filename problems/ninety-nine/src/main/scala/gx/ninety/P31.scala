package gx.ninety

/**
  * P31 (**) Determine whether a given integer number is prime.
  * scala> 7.isPrime
  * res0: Boolean = true
  */
object P31 {


  implicit class Prime(val start: Int) {

    // def isPrime: Boolean = (start > 1) && (2 to Math.sqrt(start).floor.toInt).forall(start % _ != 0)

    val primes = Stream.cons(2, Stream.from(3, 2) filter {_.isPrime})

    def isPrime: Boolean = (start > 1) && (primes takeWhile {_ <= Math.sqrt(start)} forall {start % _ != 0})

  }

}
