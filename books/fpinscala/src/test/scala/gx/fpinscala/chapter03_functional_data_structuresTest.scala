package gx.fpinscala

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by Joe on 2016/8/26.
  */
class chapter03_functional_data_structuresTest extends FlatSpec with Matchers {

  val me = chapter03_functional_data_structures

  it should "return the tail of the list" in {
    me.tail(List(1, 2, 2, 3, 4)) should be(List(2, 2, 3, 4))
  }

  it should "drop n elements from list" in {
    me.drop(List(1, 2, 3, 4), 2) should be(List(3, 4))
  }

  it should "drop elements while condition is true" in {
    me.dropWhile(List(1, 2, 3, 4, 5))(x => x < 3) should be(List(3, 4, 5))
    me.dropWhile(List(1, 2, 3, 4, 5))(x => x < 6) should be(Nil)
  }

  it should "replace the head of a list" in {
    me.setHead(List(1, 2, 3, 4), 8) should be(List(8, 2, 3, 4))
  }

  it should "return the init of the list" in {
    me.init(List(1, 2, 3, 4, 5)) should be(List(1, 2, 3, 4))
    me.init(List(1)) should be(List())
    intercept[UnsupportedOperationException] {
      me.init(List())
    }.getMessage should be("empty.init")
  }

  it should "get the length of a list" in {
    me.length(List(1, 2, 3, 4)) should be(4)
  }

  it should "test foldLeft" in {
    me.foldLeft(List(1, 2, 3, 4), 1)(_ * _) should be(24)
  }

  it should "test sum product and length2" in {
    me.sum(List(1, 2, 3, 4, 5)) should be(15)
    me.product(List(2, 4, 5)) should be(40)
    me.length2(List('a, 'b, 'c)) should be(3)
  }

  it should "reverse the list" in {
    me.reverse(List(1, 2, 3, 4)) should be(List(4, 3, 2, 1))
  }

  it should "implement foldLeft with foldRight" in {
    me.foldLeft2(List(1, 2, 3, 4), List[Int]())((a, b) => b :: a) should be(List(4, 3, 2, 1))
  }

  it should "append to the end of the list" in {
    me.append(List(1, 2), List(3, 4)) should be(List(1, 2, 3, 4))
  }

  it should "concat all into a list" in {
    me.concat(List(List(1, 2), List(3, 4), List(5))) should be(List(1, 2, 3, 4, 5))
  }

  it should "add 1 to every element" in {
    me.add1(List(1, 2, 3)) should be(List(2, 3, 4))
  }

  it should "convert double 2 string" in {
    me.doubleToString(List(1.2, 3.5)) should be(List("1.2", "3.5"))
  }

  it should "test map" in {
    me.map(List(1, 2, 3))(_ * 2) should be(List(2, 4, 6))
  }

  it should "filter all odd number" in {
    me.filter(List(1, 2, 3, 4, 5))(_ % 2 != 0) should be(List(1, 3, 5))
  }

  it should "test flatMap" in {
    me.flatMap(List(1, 2, 3))(x => List(x, x)) should be(List(1, 1, 2, 2, 3, 3))
  }

  it should "filter all even number" in {
    me.filter2(List(1, 2, 3, 4, 5))(_ % 2 == 0) should be(List(2, 4))
  }

  it should "add 2 lists" in {
    me.addList(List(1, 2, 3), List(4, 5, 6)) should be(List(5, 7, 9))
  }

  it should "add 2 list genernalize" in {
    me.addListGen(List("hello", "bye"), List("world", "bye"))((a, b) => a + " " + b) should be(List("hello world", "bye bye"))
  }

  it should "has subsequence" in {
    me.hasSubsequence(List(1, 2, 3, 4, 5), List(2, 3, 4)) should be(true)
    me.hasSubsequence(List(1, 2, 3, 4, 5), List(4, 3)) should be(false)
  }

  it should "get tree size" in {
    val t = me.Branch(me.Branch(me.Leaf(1), me.Leaf(2)),me.Branch(me.Leaf(3), me.Leaf(4)))
    me.size(t) should be(7)
    me.size2(t) should be(7)
  }

  it should "get maximum" in {
    val t = me.Branch(me.Branch(me.Leaf(1), me.Leaf(2)),me.Branch(me.Leaf(3), me.Leaf(4)))
    me.maximum(t) should be(4)
    me.maximum2(t) should be(4)
  }

  it should "get depth" in {
    val t = me.Branch(me.Branch(me.Leaf(1), me.Leaf(2)),me.Branch(me.Leaf(3), me.Branch(me.Leaf(4), me.Leaf(5))))
    me.depth(t) should be(3)
    me.depth2(t) should be(3)
  }

  it should "map function on tree" in {
    val t = me.Branch(me.Branch(me.Leaf(1), me.Leaf(2)),me.Branch(me.Leaf(3), me.Leaf(4)))
    me.map(t)(_ * 2) should be (me.Branch(me.Branch(me.Leaf(2), me.Leaf(4)),me.Branch(me.Leaf(6), me.Leaf(8))))
    me.map(t)(_ * 2) should be (me.map2(t)(_ * 2))
  }
}
