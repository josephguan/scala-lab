package gx.fpinscala

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by 10051039 on 2016/8/26.
  */
class chapter02_getting_startedTest extends FlatSpec with Matchers {

  val me = chapter02_getting_started

  it should "return the nth fib" in {
    me.fib(0) should be(0)
    me.fib(1) should be(1)
    me.fib(2) should be(1)
    me.fib(3) should be(2)
    me.fib(7) should be(13)
  }

  it should "sorted" in {
    me.isSorted(Array(1, 2, 3, 4, 5), (a: Int, b: Int) => a < b) should be(true)
    me.isSorted(Array(1, 2, 5, 4, 5), (a: Int, b: Int) => a < b) should be(false)
  }

  it should "use partial1" in {
    val twice = me.partial1(2, (a: Int, b: Int) => a * b)
    twice(3) should be(6)
  }

  it should "use curry" in {
    val func = me.curry((a: Int, b: Double) => a * b)
    val twice = func(2)
    twice(3) should be(6.0)
  }

  it should "use uncurry" in {
    val func = (a: Int) => (b: Int) => a * b
    val func2 = me.uncurry(func)
    func(2)(3) should be(func2(2, 3))
  }

  it should "compose 2 functions" in {
    def twice(a: Int): Double = a * 2.0

    def str(b: Double): String = b.toString + " double"

    val func = me.compose(str, twice)
    func(8) should be("16.0 double")
  }

}
