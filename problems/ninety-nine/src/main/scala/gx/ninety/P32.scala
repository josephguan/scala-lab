package gx.ninety

/**
  * P32 (**) Determine the greatest common divisor of two positive integer numbers.
  * Use Euclid's algorithm.
  * scala> gcd(36, 63)
  * res0: Int = 9
  */

object P32 {

  def gcd(m: Int, n: Int): Int = n match {
    case 0 => m
    case _ => gcd(n, m % n)
  }


}
