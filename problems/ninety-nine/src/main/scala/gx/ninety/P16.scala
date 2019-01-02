package gx.ninety

import scala.annotation.tailrec

/**
  * P16 (**) Drop every Nth element from a list.
  * Example:
  * scala> drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
  * res0: List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
  */
object P16 {

  def drop[A](n: Int, ls: List[A]): List[A] = {
    @tailrec
    def dropR(c: Int, curList: List[A], result: List[A]): List[A] = (c, curList) match {
      case (_, Nil) => result.reverse
      case (1, _ :: tail) => dropR(n, tail, result)
      case (_, h :: tail) => dropR(c - 1, tail, h :: result)
    }

    dropR(n, ls, Nil)
  }
}
