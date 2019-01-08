package gx.feature.delay

import java.util.Calendar

import gx.feature.delay.Delay._
import org.scalatest.{FlatSpec, Matchers}

class DelayTest extends FlatSpec with Matchers {

  it should "correct" in {
    val x = Delay(Calendar.getInstance.getTime)
    Thread.sleep(1000)
    val y = Calendar.getInstance.getTime
    Thread.sleep(1000)
    x.compute().after(y) should be(true)
  }

  it should "delay and force correctly" in {
    val x = delay(Calendar.getInstance.getTime)
    Thread.sleep(1000)
    val y = Calendar.getInstance.getTime
    Thread.sleep(1000)
    force(x).after(y) should be(true)
  }

}
