package gx.feature.subtractable

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * 升级到Scala 2.12 以后，foreach循环编译报错。
  */

object ForEach extends App {

  case class Country(name: String)

  val data: Seq[(String, String)] = Seq(("a", "name"))

  def find(name: String): Option[Country] = None

  def runValid(): ListBuffer[Country] = mutable.ListBuffer[Country]()

  def runInvalid(): mutable.Set[String] = mutable.Set[String]()

  // 如下代码编译会报错:
  // Error: type arguments [?,Iterable[java.io.Serializable] with String with Int => Any with
  // scala.collection.generic.Subtractable[_ >: String with
  // gx.feature.subtractable.Solution.Country <: java.io.Serializable,
  // Iterable[java.io.Serializable] with
  // String with Int => Any with scala.collection.generic.Subtractable[_ >: String with
  // gx.feature.subtractable.Solution.Country <: java.io.Serializable, Equals]]
  // {def seq: Iterable[java.io.Serializable] with String with Int => Any}]
  // do not conform to trait Subtractable's type parameter bounds [A,+Repr <: scala.collection.generic.Subtractable[A,Repr]]
  //
  // 开启下面代码试试：
  //    data.foreach { case (label, code) =>
  //      find(code) match {
  //        case None => runInvalid()
  //        case Some(c) => runValid()
  //      }
  //    }


  // 解决方案1： 声明返回Unit类型
  def workaroundV1(): Unit = {
    data.foreach { case (label, code) =>
      find(code) match {
        case None => runInvalid(); Unit
        case Some(c) => runValid(); Unit
      }
    }
  }

  // 解决方案2： 定义一个返回Unit的函数
  def workaroundV2(): Unit = {
    def func(label: String, code: String): Unit = {
      find(code) match {
        case None => runInvalid()
        case Some(c) => runValid()
      }
    }

    data.foreach { case (label, code) => func(label, code) }
  }

  // run test
  workaroundV1()
  workaroundV2()

}
