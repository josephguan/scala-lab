
package gx.feature.subtractable

import scala.collection.Iterable

/**
  * 升级到Scala 2.12 以后，Seq(Set("a", 1), Map((5,"x"))) 编译会报错 。
  */
object SetAndMap extends App {

  // 下面代码编译报错
  // val nestedScala = Seq(Set("a", 1), Map((5,"x")))

  // 下面是一种规避方法:
  val nestedScala1: Seq[Iterable[Any]] = Seq(Set("a", 1).toIterable, Map((5, "x")).toIterable)
  println(nestedScala1)

  // List和Set就没有问题
  val nestedScala2 = Seq(Set("a", 1), List(5, "x"))
  val nestedScala3 = Seq(Set("a", 1).toList, Map(5 -> "x").toList)
  println(nestedScala2)
  println(nestedScala3)


}
