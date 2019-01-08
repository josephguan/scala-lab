package scala // 只有在scala包中，才能访问a.tl

import scala.collection.immutable.{::, List, Nil}

/**
  * 研究Scala中ListBuffer的实现原理
  */


class MyListBuffer[A] {
  var start: List[A] = Nil
  var last0: ::[A] = _
  var exported: Boolean = false
  var len = 0

  def isEmpty: Boolean = len == 0

  def +=(x: A): this.type = {
    if (exported) copy()
    if (isEmpty) {
      last0 = new ::(x, Nil)
      start = last0
    } else {
      val last1 = last0
      println(s"before last1=${last1.tl}")
      last0 = new ::(x, Nil)
      last1.tl = last0
      println(s"after last1=${last1.tl}")
    }
    len += 1
    this
  }

  def copy() {
    println("executing copy!")
    if (isEmpty) return
    var cursor = start
    val limit = last0.tail
    clear()
    while (cursor ne limit) {
      this += cursor.head
      cursor = cursor.tail
    }
  }

  def clear() {
    start = Nil
    last0 = null
    exported = false
    len = 0
  }

  def toList: List[A] = {
    exported = !isEmpty
    start
  }

  def test(): Unit = {
    val a = new ::(1, Nil)
    val b = new ::(2, Nil)
    val c = new ::(3, Nil)
    val head = a
    a.tl = b
    println(s"head = $head")
    b.tl = c
    println(s"head = $head")
  }
}



