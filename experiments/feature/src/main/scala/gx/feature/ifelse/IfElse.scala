package gx.feature.ifelse

/**
  * Scala里没有三元条件语句，比如max = a > b ? a : b
  * 不过没关系，我们可以自己定义出类似的语法 ? |
  */

object IfElse {

  implicit class Condition(p: Boolean) {
    def ?[A](first: A): Selection[A] = new Selection(p, first)
  }

  class Selection[A](p: Boolean, first: A) {
    def |(second: A): A = p match {
      case true => first
      case _ => second
    }
  }

}
