package gx.fpinscala

import scala.annotation.tailrec

/**
  * Created by 10051039 on 2016/8/26.
  */
object chapter02_getting_started {

  /*
    EXERCISE 1 (optional): Write a function to get the th Fibonacci number. The n
    first two Fibonacci numbers are 0 and 1, and the next number is always the sum of
    the previous two. Your definition should use a local tail-recursive function.
   */
  def fib(n: Int): Int = {
    @tailrec
    def fib_rec(targetN: Int, previousValue: Int, currentValue: Int): Int = {
      if (targetN == 0) return previousValue
      fib_rec(targetN - 1, currentValue, currentValue + previousValue)
    }
    fib_rec(n, 0, 1)
  }


  /*
    EXERCISE 2: Implement isSorted, which checks whether an is isSorted Array[A]
    sorted according to a given comparison function.
   */
  def isSorted[A](as: Array[A], gt: (A, A) => Boolean): Boolean = {
    as.init zip as.tail forall { case (a, b) => gt(a, b) }
  }

  /*
    EXERCISE 3 (hard): Implement partial1 and write down a concrete usage
    of it. There is only one possible implementation that compiles.
   */
  def partial1[A, B, C](a: A, f: (A, B) => C): B => C = {
    f(a, _)
  }

  /*
    EXERCISE 4 (hard): Let's look at another example, currying, which converts a
    function of arguments into a function of one argument that returns another N
    function as its result.
   */
  def curry[A, B, C](f: (A, B) => C): A => (B => C) = {
    a => b => f(a, b)
  }

  /*
    EXERCISE 5 (optional): Implement uncurry, which reverses the
    transformation of curry. Note that since associates to the right,
    A => (B => C) can be written as A => B => C.
   */
  def uncurry[A, B, C](f: A => B => C): (A, B) => C = {
    (a, b) => f(a)(b)
  }

  /*
    EXERCISE 6: Implement the higher-order function that composes two
    functions.
   */
  def compose[A,B,C](f: B => C, g: A => B): A => C = {
    g andThen f
  }

}
