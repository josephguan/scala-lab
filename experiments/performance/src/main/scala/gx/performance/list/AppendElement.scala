package gx.performance.list

import scala.collection.mutable.ListBuffer

object AppendElement {

  def listAppend(ls: List[Int]): List[Int] = {
    var a: List[Int] = Nil
    ls.foreach(x => a =  a ::: List(x))
    a
  }

  def listPrependThenReverse(ls: List[Int]): List[Int] = {
    var a: List[Int] = Nil
    ls.foreach(x => a = x :: a)
    a.reverse
  }

  def listPrependThenReverse2(times: Int): List[Int] = {
    var a: List[Int] = Nil
    (0 until times).foreach(x => a =  x :: a)
    a.reverse
  }

  def listBufferAppend(ls: List[Int]): ListBuffer[Int] = {
    val a: ListBuffer[Int] = new ListBuffer[Int]()
    ls.foreach(x => a.append(x))
    a
  }

  def listBufferAppend2(times: Int): ListBuffer[Int] = {
    val a: ListBuffer[Int] = new ListBuffer[Int]()
    (0 until times).foreach(x => a.append(x))
    a
  }
}
