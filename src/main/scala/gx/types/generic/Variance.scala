package gx.types.generic



/**
  * MyList
  */

trait MyList[+T] {
  def isEmpty: Boolean
  def head: T
  def tail: MyList[T]

  // first implementation
  // def prepend(elem: T): List[T] = new Cons(elem, this)

  // 应该如何实现?
//  def prepend[U >: T](elem: U): MyList[U] = new Cons(elem, this)
}

class Cons[+T](val head: T, val tail: MyList[T]) extends MyList[T] {
  def isEmpty = false
}

object MyNil extends MyList[Nothing] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}


object VarianceApp extends App {
  val x: MyList[Cat] = MyNil
  val y = new Cons(new Animal(), x)
  println(y.head)

//  val z = x.prepend(new Dog())


}
