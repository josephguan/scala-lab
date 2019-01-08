package gx.feature.listbuffer

import org.scalatest.{FlatSpec, Matchers}

class MyListBufferTest extends FlatSpec with Matchers {

  it should "correct" in {
    val buf = new MyListBuffer[Int]()
    println("\nbuf += 1")
    buf += 1
    println(s"start=${buf.start}, last0=${buf.last0}, exported=${buf.exported}, len=${buf.len}")

    println("\nbuf += 2")
    buf += 2
    println(s"start=${buf.start}, last0=${buf.last0}, exported=${buf.exported}, len=${buf.len}")

    println("\nbuf += 3")
    buf += 3
    println(s"start=${buf.start}, last0=${buf.last0}, exported=${buf.exported}, len=${buf.len}")

    println("\nval x = buf.toList")
    val x = buf.toList
    println(s"start=${buf.start}, last0=${buf.last0}, exported=${buf.exported}, len=${buf.len}")
    println(s"x = $x")

    println("\nbuf += 4")
    buf += 4
    println(s"start=${buf.start}, last0=${buf.last0}, exported=${buf.exported}, len=${buf.len}")
    println(s"x = $x")

    println("\nbuf.test()")
    buf.test()

  }

}
