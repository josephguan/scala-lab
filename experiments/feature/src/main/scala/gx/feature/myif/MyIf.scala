package gx.feature.myif

/**
  * 利用Scala强大的函数式支持，我们甚至可以定义出自己的if...else控制结构
  */
object MyIf {

  class If(val condition: Boolean) {
    def myelse[A](f: => A): Unit = condition match {
      case false => f; return
      case _ => return
    }
  }

  def myif[A](condition: Boolean)(f: => A): If = condition match {
    case true => f; new If(true)
    case _ => new If(false)
  }

}
