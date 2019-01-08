package gx.performance

import java.util.Date

object Timer {

  def timing[A](name: String, func: => A): Unit = {
    val start = new Date()
    func
    val end = new Date()
    println(s"$name: totally cost ${end.getTime - start.getTime} ms")
  }

}
