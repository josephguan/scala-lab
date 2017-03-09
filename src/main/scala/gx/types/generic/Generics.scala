package gx.types.generic

// 泛型类
class Pair[A, B](val first: A, val second: B)


object GenericApp extends App {
  val p = new Pair(1, 2)
  println(p.first + ", " + p.second)

  val q = new Pair("good", 2)
  println(q.first + ", " + q.second)
  val r = changeSecond(p, "bye")

  // 泛型方法
  def changeSecond[A, B, T](p: Pair[A, B], second: T): Pair[A, T] = new Pair(p.first, second)
  println(r.first + ", " + r.second)

}

