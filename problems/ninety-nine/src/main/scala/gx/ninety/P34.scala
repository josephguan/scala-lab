package gx.ninety

import scala.annotation.tailrec

/**
  * P34 (**) Calculate Euler's totient function phi(m).
  * Euler's so-called totient function phi(m) is defined as the number of positive integers r (1 <= r <= m) that are coprime to m.
  * scala> 10.totient
  * res0: Int = 4
  */
object P34 {

  import P33._

  implicit class Totient(val n: Int) {

    def totient: Int = (1 to n) count {n.isCoprimeTo(_)}

    def totientV2: Int = {
      @tailrec
      def totientR(x: Int, acc: Int): Int = x match {
        case 1 => acc + 1
        case _ if n.isCoprimeTo(x) => totientR(x - 1, acc + 1)
        case _ => totientR(x - 1, acc)
      }
      totientR(n, 0)
    }

  }

}
