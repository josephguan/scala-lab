package gx.types.pureoo

import scala.{Boolean => ScalaBoolean}

/**
  * Scala是一个纯面向对象的语言吗？
  *
  * 一个纯面向对象的语言，意味着它的所有值都是一个对象—— type of each value is a class.
  * 对于一般语言来说，第一反应是，可能难免有些例外，比如原生类型、函数。
  *
  * 让我们看看Scala的原生类型有没有特殊的？
  * Conceptually, types such as Boolean do not receive special treatment
  * in Scala. They are like the other classes, defined in the package scala.
  */

abstract class Boolean {
  /**
    * "if (cond) then t else e"
    * could be translated to
    * "cond.ifThenElse(t, e)"
    */
  def ifThenElse[T](t: => T, e: => T): T

  def &&(x: => Boolean): Boolean = ifThenElse(x, False)

  def ||(x: => Boolean): Boolean = ifThenElse(True, x)

  def unary_! : Boolean = ifThenElse(False, True)

  def ==(x: => Boolean): Boolean = ifThenElse(x, !x)

  def !=(x: => Boolean): Boolean = ifThenElse(!x, x)

}

object True extends Boolean {
  override def ifThenElse[T](t: => T, e: => T): T = t

  override def toString: String = "True"
}


object False extends Boolean {
  override def ifThenElse[T](t: => T, e: => T): T = e

  override def toString: String = "False"
}


object BooleanApp extends App {
  val x: Boolean = True
  val y: Boolean = False
  println(x && y) // False
  println(x || y) // True
  println(!x)     // False
  println(x == y) // False
  println(x != y) // True
}